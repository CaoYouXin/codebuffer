#/bin/sh
# Tooltip fix
# A script to fix themes files in Ubuntu 11.10
# to have readable tooltips in applications such
# as eclipse.
# The script edits the gtk.css, settings.ini and gtkrc files
# Author: Victor Pillac
# http://victorpillac.wordpress.com

if [[ $EUID -ne 0 ]]; then
echo "This script must be run as root" 1>&2
exit 1
fi

path=/usr/share/themes
theme=SnowBird

if [ $# = 1 ]; then
theme=$1
fi

echo "Fixing tooltips for theme $theme"
echo " (you can select a different theme by passing its name as argument)"
sed -i 's/tooltip_bg_color #000000/tooltip_bg_color #f5f5b5/g' $path/$theme/gtk-3.0/gtk.css
sed -i 's/tooltip_fg_color #ffffff/tooltip_fg_color #000000/g' $path/$theme/gtk-3.0/gtk.css
sed -i 's/tooltip_bg_color:#000000/tooltip_bg_color:#f5f5b5/g' $path/$theme/gtk-3.0/settings.ini
sed -i 's/tooltip_fg_color:#ffffff/tooltip_fg_color:#000000/g' $path/$theme/gtk-3.0/settings.ini
sed -i 's/tooltip_bg_color:#000000/tooltip_bg_color:#f5f5b5/g' $path/$theme/gtk-2.0/gtkrc
sed -i 's/tooltip_fg_color:#ffffff/tooltip_fg_color:#000000/g' $path/$theme/gtk-2.0/gtkrc
echo "Done"
