SUMMARY = "Add IDS camera driver"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit update-rc.d

SRC_URI = " \
    file://uEyeSDK-4.90.00-ARM_LINUX_IDS_GNUEABI_HF.tgz \
	file://ueyeusbd.conf \
	file://ueyeethd.conf \
	file://machine.conf \
#    file://install-sdk-only-once.patch \
"

S = "${WORKDIR}"

RDEPENDS_${PN} = "libqtnetwork4 libqtgui4 libx11 libqtcore4"

#this is not working, /etc/rc* directories/links are not created!
#workaround using the recipe "ueye-drivers-service" that runs update-rc.d on
#the target during the first boot
INITSCRIPT_NAME = "ueyeethdrc"
INITSCRIPT_PARAMS = "defaults"

do_install() {
	#tentar ${D}${sysconfdir} = /etc
	#tentar ${D}${bindir} = /usr/bin
	#tentar ${D}${libdir} = /usr/lib
	#tentar ${D}${includedir} = /usr/include
	#tentar ${D}${localstatedir} = /var

	#this did not work
	install -d ${D}${sysconfdir}/init.d/
    install -m 0755 ${S}/etc/init.d/ueyeethdrc ${D}${sysconfdir}/init.d/ueyeethdrc
    install -m 0755 ${S}/etc/init.d/ueyeusbdrc ${D}${sysconfdir}/init.d/ueyeusbdrc

	install -d ${D}${sysconfdir}/network
	install -d ${D}${sysconfdir}/network/if-post-up.d
    install -m 0755 ${S}/etc/network/if-post-up.d/* ${D}${sysconfdir}/network/if-post-up.d
	install -d ${D}${sysconfdir}/network/if-pre-down.d
    install -m 0755 ${S}/etc/network/if-pre-down.d/* ${D}${sysconfdir}/network/if-pre-down.d

	install -d ${D}${sysconfdir}/udev
	install -d ${D}${sysconfdir}/udev/rules.d
    install -m 0644 ${S}/etc/udev/rules.d/* ${D}${sysconfdir}/udev/rules.d

	install -d ${D}${bindir}
    install -m 0755 ${S}/etc/init.d/ueyeethdrc ${D}${bindir}/ueyeethdrc
	ln -sf /usr/local/share/ueye/bin/idscameramanager ${D}${bindir}/idscameramanager
	ln -sf /usr/local/share/ueye/bin/idscameramanager ${D}${bindir}/ueyecameramanager
	ln -sf /usr/local/share/ueye/bin/ueyedemo ${D}${bindir}/ueyedemo
	ln -sf /usr/local/share/ueye/bin/ueyefreeze ${D}${bindir}/ueyefreeze
	ln -sf /usr/local/share/ueye/bin/ueyelive ${D}${bindir}/ueyelive
	ln -sf /usr/local/share/ueye/bin/ueyesetid ${D}${bindir}/ueyesetid
	ln -sf /usr/local/share/ueye/bin/ueyesetip ${D}${bindir}/ueyesetip

	oe_libinstall -so -C usr/lib libueye_api ${D}${libdir}
#	the symlink below is not automatically created:
	ln -sf /usr/lib/libueye_api.so.1 ${D}${libdir}/libueye_api.so

	install -d ${D}${includedir}
	install -m 0644 ${S}/usr/include/ueye.h ${D}/usr/include/
	install -m 0644 ${S}/usr/include/ueye_deprecated.h ${D}/usr/include/
	ln -sf /usr/include/ueye.h ${D}/usr/include/uEye.h

	install -d ${D}/usr/local/share/ueye/bin
	install -m 0755 ${S}/usr/local/share/ueye/bin/* ${D}/usr/local/share/ueye/bin

	install -d ${D}/usr/local/share/ueye/firmware/usb3
	install -m 0644 ${S}/usr/local/share/ueye/firmware/usb3/* ${D}/usr/local/share/ueye/firmware/usb3

	install -d ${D}/usr/local/share/ueye/firmware/usb3_addon
	install -m 0644 ${S}/usr/local/share/ueye/firmware/usb3_addon/* ${D}/usr/local/share/ueye/firmware/usb3_addon

	install -d ${D}/usr/local/share/ueye/licenses
	install -m 0644 ${S}/usr/local/share/ueye/licenses/* ${D}/usr/local/share/ueye/licenses

	install -d ${D}/usr/local/share/ueye/ueyeethd
	install -m 0755 ${S}/usr/local/share/ueye/ueyeethd/* ${D}/usr/local/share/ueye/ueyeethd
	install -m 0644 ${WORKDIR}/ueyeethd.conf ${D}/usr/local/share/ueye/ueyeethd

	install -d ${D}/usr/local/share/ueye/ueyeusbd
	install -m 0755 ${S}/usr/local/share/ueye/ueyeusbd/* ${D}/usr/local/share/ueye/ueyeusbd
	install -m 0644 ${WORKDIR}/ueyeusbd.conf ${D}/usr/local/share/ueye/ueyeusbd

	install -d ${D}/usr/local/share/ueye/libueye_api
	install -m 0644 ${WORKDIR}/machine.conf ${D}/usr/local/share/ueye/libueye_api
}

FILES_${PN} += "${sysconfdir}/* ${bindir}/* ${libdir}/* /usr/local/* ${includedir}/*"

PACKAGE_ARCH = "${MACHINE_ARCH}"

# Requeired due to precompiled binaries/libs
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

INSANE_SKIP_${PN} += "already-stripped ldflags"

