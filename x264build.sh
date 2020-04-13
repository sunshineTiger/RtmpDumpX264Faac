#!/bin/bash
NDK=/home/zh/android-ndk-r20b
SYSROOT=$NDK/platforms/android-21/arch-arm/
TOOLCHAIN=$NDK/toolchains/arm-linux-androideabi-4.9/prebuilt/linux-x86_64


build_one()
{
./configure \
--prefix=$PREFIX \
--enable-shared \
--enable-static \
--disable-gpac \
--disable-cli \
--cross-prefix=$TOOLCHAIN/bin/arm-linux-androideabi- \
--host=arm-linux \
--sysroot=$SYSROOT \
--extra-cflags="-Os -fpic $ADDI_CFLAGS" \
--extra-ldflags="$ADDI_LDFLAGS" \
$ADDITIONAL_CONFIGURE_FLAG
}
CPU=arm
PREFIX=$(pwd)/android/$CPU
ADDI_CFLAGS=""

build_one

