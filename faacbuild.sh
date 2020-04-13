#!bin/bash
NDK=/home/zh/android-ndk-r20b
TOOLCHAIN=$NDK/toolchains/arm-linux-androideabi-4.9/prebuilt/linux-x86_64  
export PLATFORM=$NDK/platforms/android-21/arch-arm/
build_faac()
{  
pwd  
CFLAGS="-fpic -DANDROID -fpic  -mthumb-interwork -ffunction-sections -funwind-tables -fstack-protector -fno-short-enums -D__ARM_ARCH_5__ -D__ARM_ARCH_5T__ -D__ARM_ARCH_5E__ -D__ARM_ARCH_5TE__ -Wno-psabi -march=armv5te -mtune=xscale -msoft-float -mthumb -Os -fomit-frame-pointer -fno-strict-aliasing -finline-limit=64 -DANDROID -Wa,--noexecstack -MMD -MP "  
#FLAGS="--host=arm-androideabi-linux --enable-static --enable-shared --prefix=$HOME --enable-armv5e "  
CROSS_COMPILE=$TOOLCHAIN/bin/arm-linux-androideabi-  
export CPPFLAGS="$CFLAGS"  
export CFLAGS="$CFLAGS"  
export CXXFLAGS="$CFLAGS"  
export CXX="${CROSS_COMPILE}g++ --sysroot=${PLATFORM}"  
export LDFLAGS="$LDFLAGS"  
export CC="${CROSS_COMPILE}gcc --sysroot=${PLATFORM}"  
export NM="${CROSS_COMPILE}nm"  
export STRIP="${CROSS_COMPILE}strip"  
export RANLIB="${CROSS_COMPILE}ranlib"  
export AR="${CROSS_COMPILE}ar"  
./configure --prefix="/home/zh/faac-1.29/install/" --enable-static --enable-shared  --host=arm-linux --with-pic
}  
build_faac  
