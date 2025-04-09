#Write a script that finds in a given directory hierarchy, all duplicate files and displays their paths. Hint: use 
#checksums to detect whether two files are identical.
#!/bin/bash

index1=0
find -ls -type f | awk '{print $3, $7, $11}' | while read line
do
        index2=0
        find -ls -type f | awk '{print $3, $7, $11}' | while read line2
        do
                line1_checksum=`echo $line | awk '{print $1, $2}' | md5sum`
                line2_checksum=`echo $line2 | awk '{print $1, $2}' | md5sum`

                if [ "$line1_checksum" == "$line2_checksum" ] && [ $index1 != $index2 ] ; then
                        echo -n `echo $line | awk '{print $3}'`
                        echo -n " = "
                        echo -n `echo $line2 | awk '{print $3}'`
                        echo ""
                fi
                ((index2++))
        done
        ((index1++))
done
