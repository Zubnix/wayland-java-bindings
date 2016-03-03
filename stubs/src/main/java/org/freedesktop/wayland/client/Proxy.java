//Copyright 2015 Erik De Rijcke
//
//Licensed under the Apache License,Version2.0(the"License");
//you may not use this file except in compliance with the License.
//You may obtain a copy of the License at
//
//http://www.apache.org/licenses/LICENSE-2.0
//
//Unless required by applicable law or agreed to in writing,software
//distributed under the License is distributed on an"AS IS"BASIS,
//WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,either express or implied.
//See the License for the specific language governing permissions and
//limitations under the License.
package org.freedesktop.wayland.client;

import com.github.zubnix.jaccall.Pointer;
import org.freedesktop.wayland.client.jaccall.WaylandClientCore;
import org.freedesktop.wayland.util.Arguments;
import org.freedesktop.wayland.util.Dispatcher;
import org.freedesktop.wayland.util.InterfaceMeta;
import org.freedesktop.wayland.util.ObjectCache;
import org.freedesktop.wayland.util.WaylandObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a protocol object on the client side.
 * <p/>
 * A {@code Proxy} acts as a client side proxy to an object existing in the
 * compositor. The proxy is responsible for converting requests made by the
 * clients with {@link #marshal(int)} into Wayland's wire format. Events
 * coming from the compositor are also handled by the proxy, which will in
 * turn call the implementation.
 * <p/>
 * With the exception of function {@link #setQueue(EventQueue)}, functions
 * accessing a {@code Proxy} are not normally used by client code. Clients
 * should normally use the higher level interface generated by the scanner to
 * interact with compositor objects.
 * <p/>
 *
 * @param <I> Implementation type that will act as the listener for received events.
 */
public abstract class Proxy<I> implements WaylandObject {

    private static final Map<Class<? extends Proxy<?>>, Constructor<? extends Proxy<?>>> PROXY_CONSTRUCTORS = new HashMap<Class<? extends Proxy<?>>, Constructor<? extends Proxy<?>>>();

    public final Long pointer;

    private final int             version;
    private final I               implementation;
    private final Pointer<Object> jObjectPointer;

    protected Proxy(final long pointer) {
        this(pointer,
             null,
             99);
    }

    /**
     * @param pointer
     * @param implementation The listener to be added to proxy
     * @param version
     */
    protected Proxy(final Long pointer,
                    final I implementation,
                    final int version) {
        this.pointer = pointer;
        this.implementation = implementation;
        this.version = version;
        ObjectCache.store(this.pointer,
                          this);
        this.jObjectPointer = Pointer.from(this);

        //Special casing implementation. For some proxies the underlying native library provides its own implementation.
        //We pass in a null implementation in those cases. (Eg Display proxy).
        if (implementation != null) {
            WaylandClientCore.INSTANCE()
                             .wl_proxy_add_dispatcher(this.pointer,
                                                      Dispatcher.INSTANCE.address,
                                                      jObjectPointer.address,
                                                      0L);
        }
    }

    //TODO add get(Pointer) method for each generated proxy

    //called from generated proxies

    /**
     * Prepare a request to be sent to the compositor
     * <p/>
     * This function is similar to {@link #marshalConstructor(int, Object, int, Class, Arguments)}, except
     * it doesn't create proxies for new-id arguments.
     * <p/>
     * This should not normally be used by non-generated code.
     *
     * @param opcode Opcode of the request to be sent
     * @param args   Extra arguments for the given request
     */
    protected void marshal(final int opcode,
                           final Arguments args) {
        WaylandClientCore.INSTANCE()
                         .wl_proxy_marshal_array(this.pointer,
                                                 opcode,
                                                 args.pointer.address);
        args.pointer.close();
    }

    /**
     * @param opcode Opcode of the request to be sent
     *
     * @see {@link #marshal(int, Arguments)}
     */
    protected void marshal(final int opcode) {
        WaylandClientCore.INSTANCE()
                         .wl_proxy_marshal_array(this.pointer,
                                                 opcode,
                                                 0L);
    }

    //called from generated proxies

    /**
     * Prepare a request to be sent to the compositor
     * <p/>
     * Translates the request given by opcode and the extra arguments into the
     * wire format and write it to the connection buffer.
     * <p/>
     * For new-id arguments, this function will allocate a new {@code Proxy}
     * and send the ID to the server.  The new {@code Proxy} will be returned
     * on success or NULL on errror with errno set accordingly.
     * <p/>
     * This is intended to be used by language bindings and not in
     * non-generated code.
     *
     * @param opcode         Opcode of the request to be sent
     * @param implementation The listener to use for the new proxy
     * @param version        The runtime version of the new proxy
     * @param newProxyCls    The type to use for the new proxy
     * @param args           Extra arguments for the given request
     * @param <J>            implementation Type
     * @param <T>            proxy Type
     *
     * @return a new proxy
     */
    protected <J, T extends Proxy<J>> T marshalConstructor(final int opcode,
                                                           final J implementation,
                                                           final int version,
                                                           final Class<T> newProxyCls,
                                                           final Arguments args) {
        final T t = marshalConstructor(opcode,
                                       implementation,
                                       version,
                                       newProxyCls,
                                       args.pointer.address);
        args.pointer.close();
        return t;
    }

    //called from generated proxies
    private <J, T extends Proxy<J>> T marshalConstructor(final int opcode,
                                                         final J implementation,
                                                         final int version,
                                                         final Class<T> newProxyCls,
                                                         final long argsPointer) {
        try {
            final long wlProxy = WaylandClientCore.INSTANCE()
                                                  .wl_proxy_marshal_array_constructor(this.pointer,
                                                                                      opcode,
                                                                                      argsPointer,
                                                                                      InterfaceMeta.get(newProxyCls).pointer.address);
            return marshalProxy(wlProxy,
                                implementation,
                                version,
                                newProxyCls);
        }
        catch (final NoSuchMethodException e) {
            throw new RuntimeException("Uh oh, this is a bug!",
                                       e);
        }
        catch (final IllegalAccessException e) {
            throw new RuntimeException("Uh oh, this is a bug!",
                                       e);
        }
        catch (final InvocationTargetException e) {
            throw new RuntimeException("Uh oh, this is a bug!",
                                       e);
        }
        catch (final InstantiationException e) {
            throw new RuntimeException("Uh oh, this is a bug!",
                                       e);
        }
    }

    private <J, T extends Proxy<J>> T marshalProxy(final long pointer,
                                                   final J implementation,
                                                   final int version,
                                                   final Class<T> newProxyCls) throws NoSuchMethodException,
                                                                                      IllegalAccessException,
                                                                                      InvocationTargetException,
                                                                                      InstantiationException {
        Constructor<? extends Proxy<?>> proxyConstructor = PROXY_CONSTRUCTORS.get(newProxyCls);
        if (proxyConstructor == null) {
            proxyConstructor = findMatchingConstructor(newProxyCls,
                                                       Pointer.class,
                                                       implementation.getClass(),
                                                       int.class);
            PROXY_CONSTRUCTORS.put(newProxyCls,
                                   proxyConstructor);
        }
        return (T) proxyConstructor.newInstance(pointer,
                                                implementation,
                                                version);
    }

    private <J, T extends Proxy<J>> Constructor<T> findMatchingConstructor(final Class<T> newProxyCls,
                                                                           final Class<?> pointerClass,
                                                                           final Class<?> implementationClass,
                                                                           final Class<?> intClass) throws NoSuchMethodException {
        for (final Constructor<?> constructor : newProxyCls.getConstructors()) {
            final Class<?>[] parameterTypes = constructor.getParameterTypes();
            if (parameterTypes.length != 3) {
                continue;
            }
            if (parameterTypes[0].isAssignableFrom(pointerClass) &&
                parameterTypes[1].isAssignableFrom(implementationClass) &&
                parameterTypes[2].isAssignableFrom(intClass)) {
                return (Constructor<T>) constructor;
            }
        }
        throw new NoSuchMethodException();
    }

    /**
     * Get a proxy's listener
     * <p/>
     * Gets the proxy's listener; which is the implementation set when this proxy was constructed.
     * <p/>
     * This function is useful in client with multiple listeners on the same
     * interface to allow the identification of which code to execute.
     *
     * @return The proxy's listener or NULL if no listener is set
     */
    public I getImplementation() {
        return this.implementation;
    }

    /**
     * Get the id of a proxy object
     *
     * @return The id the object associated with the proxy
     */
    public int getId() {
        return WaylandClientCore.INSTANCE()
                                .wl_proxy_get_id(this.pointer);
    }

    public int getVersion() {
        return this.version;
    }

    /**
     * Assign a proxy to an event queue
     * <p/>
     * Assign proxy to event queue. Events coming from {@code proxy} will be
     * queued in {@code queue} instead of the display's main queue.
     *
     * @param queue The event queue that will handle this proxy
     *
     * @see Display#dispatchQueue(EventQueue)
     */
    public void setQueue(final EventQueue queue) {
        WaylandClientCore.INSTANCE()
                         .wl_proxy_set_queue(this.pointer,
                                             queue.pointer);
    }

    @Override
    public int hashCode() {
        return this.pointer.hashCode();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Proxy)) {
            return false;
        }

        final Proxy proxy = (Proxy) o;

        return this.pointer.equals(proxy.pointer);
    }

    /**
     * Destroy a proxy object
     */
    public void destroy() {
        WaylandClientCore.INSTANCE()
                         .wl_proxy_destroy(this.pointer);
        ObjectCache.remove(this.pointer);
        this.jObjectPointer.close();
    }
}