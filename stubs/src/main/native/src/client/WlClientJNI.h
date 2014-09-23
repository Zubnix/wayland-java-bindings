/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class org_freedesktop_wayland_client_WlClientJNI */

#ifndef _Included_org_freedesktop_wayland_client_WlClientJNI
#define _Included_org_freedesktop_wayland_client_WlClientJNI
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     org_freedesktop_wayland_client_WlClientJNI
 * Method:    connect
 * Signature: (Ljava/lang/String;)J
 */
JNIEXPORT jlong JNICALL Java_org_freedesktop_wayland_client_WlClientJNI_connect__Ljava_lang_String_2
  (JNIEnv *, jclass, jstring);

/*
 * Class:     org_freedesktop_wayland_client_WlClientJNI
 * Method:    connect
 * Signature: (I)J
 */
JNIEXPORT jlong JNICALL Java_org_freedesktop_wayland_client_WlClientJNI_connect__I
  (JNIEnv *, jclass, jint);

/*
 * Class:     org_freedesktop_wayland_client_WlClientJNI
 * Method:    disconnect
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_freedesktop_wayland_client_WlClientJNI_disconnect
  (JNIEnv *, jclass, jlong);

/*
 * Class:     org_freedesktop_wayland_client_WlClientJNI
 * Method:    getFD
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_org_freedesktop_wayland_client_WlClientJNI_getFD
  (JNIEnv *, jclass, jlong);

/*
 * Class:     org_freedesktop_wayland_client_WlClientJNI
 * Method:    dispatch
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_org_freedesktop_wayland_client_WlClientJNI_dispatch
  (JNIEnv *, jclass, jlong);

/*
 * Class:     org_freedesktop_wayland_client_WlClientJNI
 * Method:    dispatchPending
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_org_freedesktop_wayland_client_WlClientJNI_dispatchPending
  (JNIEnv *, jclass, jlong);

/*
 * Class:     org_freedesktop_wayland_client_WlClientJNI
 * Method:    dispatchQueue
 * Signature: (JJ)I
 */
JNIEXPORT jint JNICALL Java_org_freedesktop_wayland_client_WlClientJNI_dispatchQueue
  (JNIEnv *, jclass, jlong, jlong);

/*
 * Class:     org_freedesktop_wayland_client_WlClientJNI
 * Method:    dispatchQueuePending
 * Signature: (JJ)I
 */
JNIEXPORT jint JNICALL Java_org_freedesktop_wayland_client_WlClientJNI_dispatchQueuePending
  (JNIEnv *, jclass, jlong, jlong);

/*
 * Class:     org_freedesktop_wayland_client_WlClientJNI
 * Method:    flush
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_org_freedesktop_wayland_client_WlClientJNI_flush
  (JNIEnv *, jclass, jlong);

/*
 * Class:     org_freedesktop_wayland_client_WlClientJNI
 * Method:    roundtrip
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_org_freedesktop_wayland_client_WlClientJNI_roundtrip
  (JNIEnv *, jclass, jlong);

/*
 * Class:     org_freedesktop_wayland_client_WlClientJNI
 * Method:    createQueue
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_org_freedesktop_wayland_client_WlClientJNI_createQueue
  (JNIEnv *, jclass, jlong);

/*
 * Class:     org_freedesktop_wayland_client_WlClientJNI
 * Method:    getError
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_org_freedesktop_wayland_client_WlClientJNI_getError
  (JNIEnv *, jclass, jlong);

/*
 * Class:     org_freedesktop_wayland_client_WlClientJNI
 * Method:    prepareReadQueue
 * Signature: (JJ)I
 */
JNIEXPORT jint JNICALL Java_org_freedesktop_wayland_client_WlClientJNI_prepareReadQueue
  (JNIEnv *, jclass, jlong, jlong);

/*
 * Class:     org_freedesktop_wayland_client_WlClientJNI
 * Method:    prepareRead
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_org_freedesktop_wayland_client_WlClientJNI_prepareRead
  (JNIEnv *, jclass, jlong);

/*
 * Class:     org_freedesktop_wayland_client_WlClientJNI
 * Method:    cancelRead
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_freedesktop_wayland_client_WlClientJNI_cancelRead
  (JNIEnv *, jclass, jlong);

/*
 * Class:     org_freedesktop_wayland_client_WlClientJNI
 * Method:    readEvents
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_org_freedesktop_wayland_client_WlClientJNI_readEvents
  (JNIEnv *, jclass, jlong);

/*
 * Class:     org_freedesktop_wayland_client_WlClientJNI
 * Method:    destroyEventQueue
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_freedesktop_wayland_client_WlClientJNI_destroyEventQueue
  (JNIEnv *, jclass, jlong);

/*
 * Class:     org_freedesktop_wayland_client_WlClientJNI
 * Method:    marshal
 * Signature: (JIJ)V
 */
JNIEXPORT void JNICALL Java_org_freedesktop_wayland_client_WlClientJNI_marshal
  (JNIEnv *, jclass, jlong, jint, jlong);

/*
 * Class:     org_freedesktop_wayland_client_WlClientJNI
 * Method:    destroy
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_freedesktop_wayland_client_WlClientJNI_destroy
  (JNIEnv *, jclass, jlong);

/*
 * Class:     org_freedesktop_wayland_client_WlClientJNI
 * Method:    getListener
 * Signature: (J)Ljava/lang/Object;
 */
JNIEXPORT jobject JNICALL Java_org_freedesktop_wayland_client_WlClientJNI_getListener
  (JNIEnv *, jclass, jlong);

/*
 * Class:     org_freedesktop_wayland_client_WlClientJNI
 * Method:    getProxyId
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_org_freedesktop_wayland_client_WlClientJNI_getProxyId
  (JNIEnv *, jclass, jlong);

/*
 * Class:     org_freedesktop_wayland_client_WlClientJNI
 * Method:    setQueue
 * Signature: (JJ)V
 */
JNIEXPORT void JNICALL Java_org_freedesktop_wayland_client_WlClientJNI_setQueue
  (JNIEnv *, jclass, jlong, jlong);

/*
 * Class:     org_freedesktop_wayland_client_WlClientJNI
 * Method:    marshalConstructor
 * Signature: (JIJJ)J
 */
JNIEXPORT jlong JNICALL Java_org_freedesktop_wayland_client_WlClientJNI_marshalConstructor
  (JNIEnv *, jclass, jlong, jint, jlong, jlong);

/*
 * Class:     org_freedesktop_wayland_client_WlClientJNI
 * Method:    addDispatcher
 * Signature: (JLjava/lang/Object;)V
 */
JNIEXPORT void JNICALL Java_org_freedesktop_wayland_client_WlClientJNI_addDispatcher
  (JNIEnv *, jclass, jlong, jobject);

#ifdef __cplusplus
}
#endif
#endif
