    #!/bin/bash  
    	PREBUILT=/home/zhoujj/develop/android-ndk-r9/toolchains/arm-linux-androideabi-4.6/prebuilt/linux-x86
        PLATFORM=/home/zhoujj/develop/android-ndk-r9/platforms/android-9/arch-arm
	
	./configure --target-os=linux \
        --arch=arm \
	--enable-version3 \
	--enable-gpl \
	--enable-nonfree \
	--disable-doc \
	--disable-stripping \
	--disable-ffmpeg \
	--disable-ffplay \
	--disable-ffserver \
	--disable-ffprobe \
	--disable-encoders \
	--disable-muxers \
	--disable-devices \
	--enable-avfilter \
	--enable-network \
	--enable-protocol=tcp \
	--enable-avfilter \
	--enable-demuxer=flv \
	--enable-demuxer=h264 \
	--enable-parser=h264 \
	--enable-parser=aac \
	--enable-decoder=h264 \
	--enable-decoder=rawvideo \
	--disable-librtmp \
	--disable-avdevice \
	--disable-asm \
	--enable-neon \
	--enable-cross-compile \
	--cc=$PREBUILT/bin/arm-linux-androideabi-gcc\
	--cross-prefix=$PREBUILT/bin/arm-linux-androideabi- \
	--nm=$PREBUILT/bin/arm-linux-androideabi-nm \
	--strip=$PREBUILT/bin/arm-linux-androideabi-strip \
	--extra-cflags="-fPIC -DANDROID" \
	--extra-ldflags="-Wl,-T,$PREBUILT/arm-linux-androideabi/lib/ldscripts/armelfb_linux_eabi.x -Wl,-rpath-link=$PLATFORM/usr/lib -L$PLATFORM/usr/lib -nostdlib $PREBUILT/lib/gcc/arm-linux-androideabi/4.6/crtbegin.o $PREBUILT/lib/gcc/arm-linux-androideabi/4.6/crtend.o -lc -lm -ldl"  
