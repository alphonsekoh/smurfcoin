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

JNIEXPORT jstring  JNICALL
Java_edu_singaporetech_btco_BTCOActivity_mineGenesis(JNIEnv *env, jobject thiz, jint diff) {
        char genesisData[] = "The Times 03/Jan/2009 Chancellor on brink of second bailout for banks";
        BlockHeader genBlock = addBlockWithPrevPtr(NULL, genesisData,sizeof(genesisData), diff);
        char hashStr[HASH_LEN*2+1];
        makeCStringFromBytes(genBlock.dataHash, hashStr, HASH_LEN);
        __android_log_print(ANDROID_LOG_DEBUG, TAG, "genesis block hash=%s", hashStr);
        mine(&genBlock, diff);
        return (*env)->NewStringUTF(env, hashStr);
}

JNIEXPORT jstring JNICALL
Java_edu_singaporetech_btco_BTCOActivity_mineChain(JNIEnv *env, jobject thiz, jint diff,
                                                   jint blocks, jstring message) {
    char genesisData[] = "The Times 03/Jan/2009 Chancellor on brink of second bailout for banks";
    char hashStr[HASH_LEN*2+1];


    BlockHeader genesis =  addBlockWithPrevPtr(NULL, genesisData,sizeof(message), diff);
    mine(&genesis, diff);
    BlockHeader *prevHeader = &genesis;
    for(int i = 1; i < blocks; i++) {
        BlockHeader currHead = addBlockWithPrevPtr(prevHeader, message,sizeof(message), diff);
        makeCStringFromBytes(currHead.dataHash, hashStr, HASH_LEN);
        mine(&currHead, diff);
        prevHeader = &currHead;
    }

    __android_log_print(ANDROID_LOG_DEBUG, TAG, "genesis block hash=%s", hashStr);
    return (*env)->NewStringUTF(env, hashStr);
}

