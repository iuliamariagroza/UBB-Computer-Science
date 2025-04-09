#Display all the mounted file systems who are either smaller than than 1GB or have less than 20% free space.

#!/bin/bash
df -BG | tail -n+2 | awk '{print $2, $5, $6}' | while read line
do
        size=`echo $line | awk '{print $1}' | sed "s/G//"`
        used=`echo $line | awk '{print $2}' | sed "s/%//"`

        if [ $size -lt 20 ] || [ $used -gt 80 ] ; then
                echo $line | awk '{print $3}'
        fi
done
