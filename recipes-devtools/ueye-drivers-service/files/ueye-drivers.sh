#!/bin/sh

if [ -f /usr/local/share/ueye/installed.txt ]; then exit; fi

update-rc.d ueyeusbdrc defaults
update-rc.d ueyeethdrc defaults

echo "uEye SDK update-rc.d executed" >> /usr/local/share/ueye/installed.txt
