#!/bin/bash

# 定义变量方便修改
APP=gmall

# 如果是输入的日期按照取输入日期；如果没输入日期取当前时间的前一天
if [ -n "$1" ] ;then
	   do_date=$1
	 
   else
	      do_date=`date -d "-1 day" +%F`
	       
      fi

      echo ================== 日志日期为 $do_date ==================
      sql="
      load data inpath '/origin_data/$APP/log/topic_log/$do_date' into table ${APP}.ods_log partition(dt='$do_date');
      "

      hive -e "$sql"

      hadoop jar /home/hadoop/app/hadoop/share/hadoop/common/hadoop-lzo-0.4.21-SNAPSHOT.jar com.hadoop.compression.lzo.DistributedLzoIndexer /warehouse/$APP/ods/ods_log/dt=$do_date
