#include <jni.h>

// Write C++ code here.
//
// Do not forget to dynamically load the C++ library into your application.
//
// For instance,
//
// In MainActivity.java:
//    static {
//       System.loadLibrary("btco");
//    }
//
// Or, in MainActivity.kt:
//    companion object {
//      init {
//         System.loadLibrary("btco")
//      }
//    }
#include <jni.h>
#include <string.h>
#include <android/log.h>
#define TAG "BTCONATIVE"
JNIEXPORT void JNICALL
Java_edu_singaporetech_btco_BTCOActivity_VerifyInput(JNIEnv *env, jobject thiz, jstring diff, jstring message) {
        __android_log_print(ANDROID_LOG_INFO, TAG, "difficult=%s message=\"%s\"", (*env)->GetStringUTFChars(env, diff, 0), (*env)->GetStringUTFChars(env, message, 0));

}