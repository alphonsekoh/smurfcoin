#include <jni.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <string.h>
#include <time.h>
#include <memory.h>
#include <assert.h>
#include <android/log.h>
#include "blockchain.h"
#include "sha-256.h"

#define TAG "BTCONATIVE"
JNIEXPORT void JNICALL
Java_edu_singaporetech_btco_BTCOActivity_VerifyInput(JNIEnv *env, jobject thiz, jstring diff, jstring message) {
        __android_log_print(ANDROID_LOG_INFO, TAG, "difficult=%s message=\"%s\"", (*env)->GetStringUTFChars(env, diff, 0), (*env)->GetStringUTFChars(env, message, 0));
}

JNIEXPORT void JNICALL
Java_edu_singaporetech_btco_BTCOActivity_mineGenesis(JNIEnv *env, jobject thiz, jint diff) {
        char genesisData[] = "The Times 03/Jan/2009 Chancellor on brink of second bailout for banks";
         addBlockWithPrevPtr(NULL, genesisData,sizeof(genesisData), diff);
}