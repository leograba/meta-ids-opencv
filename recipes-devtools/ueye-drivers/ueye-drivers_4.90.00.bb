SUMMARY = "Add IDS camera driver"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit systemd

SRC_URI = " \
    file://uEyeSDK-4.90.00-ARM_LINUX_IDS_GNUEABI_HF.tgz \
    file://ueye-drivers.service \
"

S = "${WORKDIR}"

do_install() {
	#tentar ${D}${sysconfdir} = /etc
	#tentar ${D}${bindir} = /usr/bin
	#tentar ${D}${libdir} = /usr/lib
	#tentar ${D}${includedir} = /usr/include
	#tentar ${D}${localstatedir} = /var

	install -d ${D}/etc

	install -d ${D}/etc/init.d
    install -m 0755 ${S}/etc/init.d/* ${D}/etc/init.d

	install -d ${D}/etc/network
	install -d ${D}/etc/network/if-post-up.d
    install -m 0755 ${S}/etc/network/if-post-up.d/* ${D}/etc/network/if-post-up.d
	install -d ${D}/etc/network/if-pre-down.d
    install -m 0755 ${S}/etc/network/if-pre-down.d/* ${D}/etc/network/if-pre-down.d

	install -d ${D}/etc/udev
	install -d ${D}/etc/udev/rules.d
    install -m 0644 ${S}/etc/udev/rules.d/* ${D}/etc/udev/rules.d

	install -d ${D}/usr

	install -d ${D}/usr/bin
	ln -sf /usr/local/share/ueye/bin/idscameramanager ${D}/usr/bin/idscameramanager
	ln -sf /usr/local/share/ueye/bin/idscameramanager ${D}/usr/bin/ueyecameramanager
	ln -sf /usr/local/share/ueye/bin/ueyedemo ${D}/usr/bin/ueyedemo
	ln -sf /usr/local/share/ueye/bin/ueyefreeze ${D}/usr/bin/ueyefreeze
	ln -sf /usr/local/share/ueye/bin/ueyelive ${D}/usr/bin/ueyelive
	ln -sf /usr/local/share/ueye/bin/ueyesetid ${D}/usr/bin/ueyesetid
	ln -sf /usr/local/share/ueye/bin/ueyesetip ${D}/usr/bin/ueyesetip

	install -d ${D}/usr/include
	install -m 0644 ${S}/usr/include/ueye.h ${D}/usr/include/
	install -m 0644 ${S}/usr/include/ueye_deprecated.h ${D}/usr/include/
	ln -sf /usr/include/ueye.h ${D}/usr/include/uEye.h

	#do we need to create the symlinks?
	install -d ${D}/usr/lib
	oe_libinstall -so -C usr/lib libueye_api ${D}/usr/lib
	ln -sf /usr/lib/libueye_api.so ${D}/usr/lib/libueye_api.so.4.90
	ln -sf /usr/lib/libueye_api.so.1 ${D}/usr/lib/libueye_api.so
	
	install -d ${D}/usr/local
	install -d ${D}/usr/local/share
	install -d ${D}/usr/local/share/ueye
	install -d ${D}/usr/local/share/ueye/bin
	install -d ${D}/usr/local/share/ueye/firmware
	install -d ${D}/usr/local/share/ueye/firmware/usb3
	install -d ${D}/usr/local/share/ueye/firmware/usb3_addon
	install -d ${D}/usr/local/share/ueye/licenses
	install -d ${D}/usr/local/share/ueye/ueyeethd
	install -d ${D}/usr/local/share/ueye/ueyeusbd
	install -m 0755 ${S}/usr/local/share/ueye/bin/* ${D}/usr/local/share/ueye/bin
	install -m 0644 ${S}/usr/local/share/ueye/firmware/usb3/* ${D}/usr/local/share/ueye/firmware/usb3
	install -m 0644 ${S}/usr/local/share/ueye/firmware/usb3_addon/* ${D}/usr/local/share/ueye/firmware/usb3_addon
	install -m 0644 ${S}/usr/local/share/ueye/licenses/* ${D}/usr/local/share/ueye/licenses
	install -m 0755 ${S}/usr/local/share/ueye/ueyeethd/* ${D}/usr/local/share/ueye/ueyeethd
	install -m 0755 ${S}/usr/local/share/ueye/ueyeusbd/* ${D}/usr/local/share/ueye/ueyeusbd

    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/ueye-drivers.service ${D}${systemd_unitdir}/system
}

FILES_${PN} += "/usr/local/share/ueye/*"

NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "ueye-drivers.service"

PACKAGE_ARCH = "${MACHINE_ARCH}"

