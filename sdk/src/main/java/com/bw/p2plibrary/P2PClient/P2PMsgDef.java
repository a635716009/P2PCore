package com.bw.p2plibrary.P2PClient;

/**
 * Created by Administrator on 2017/1/3.
 */

public interface P2PMsgDef
{
     String  g_str_msg_dev_manual_get_time = "msg_dev_manual_get_time";                //获取时间
     String g_str_msg_dev_manual_set_time = "msg_dev_manual_set_time";                  //设置时间
     String g_str_msg_dev_get_config_ntp = "msg_dev_get_config_ntp";                  //获取NTP校时
     String g_str_msg_dev_set_config_ntp = "msg_dev_set_config_ntp";                  //设置NTP校时

//用户管理
     String g_str_msg_dev_user_get = "msg_dev_get_config_ipc_user";                                      /* 获取用户列表 */
     String g_str_msg_dev_user_set = "msg_dev_set_config_ipc_user";                                      /* 设置用户信息 */
     String g_str_msg_dev_user_delete = "msg_dev_delete_config_ipc_user";                                /* 删除用户信息 */
//云台控制
     String g_str_msg_dev_get_config_ptz = "msg_dev_get_config_ptz";                                        /* 获取云台信息 */
     String g_str_msg_dev_set_config_ptz = "msg_dev_set_config_ptz";                                        /* 设置云台信息（使能，巡航等） */
     String g_str_msg_dev_get_config_ptz_preset = "msg_dev_get_config_ptz_preset";                          /* 获取预置位信息 */
     String g_str_msg_dev_ptz_preset_set = "msg_dev_set_config_ptz_preset";                          /* 预置位相关信息设置 */
     String g_str_msg_dev_ptz_sensor_get = "msg_dev_get_config_sensor";                          /* 获取传感器列表 */
     String g_str_msg_dev_ptz_sensor_set = "msg_dev_set_config_sensor";                          /* 设置传感器信息 */
     String g_str_msg_dev_ptz_position_set = "msg_dev_ptz_position_set";                      /* 控制云台位置 */
     String g_str_msg_dev_ptz_sensor_register_set = "msg_dev_ptz_sensor_register_set";        /* 传感器Id注册获取id */
     String g_str_msg_dev_ptz_preset_position_set = "msg_dev_ptz_preset_position_set";        /* 预置位位置设置 */
     String g_str_msg_dev_ptz_sensor_bind_set = "msg_dev_ptz_sensor_bind_set";                /* 传感器预置位绑定 */
     String g_str_msg_dev_get_config_ptz_preset_bind_sensor = "msg_dev_get_config_ptz_preset_bind_sensor";                /* 获取传感器预置位绑定信息 */
     String g_str_msg_dev_ptz_sensor_unbind_set = "msg_dev_ptz_sensor_unbind_set";            /* 传感器预置位解绑 */
     String g_str_msg_dev_ptz_sensor_alarm_set = "msg_dev_ptz_sensor_alarm_set";              /* 传感器报警*/
     String g_str_msg_dev_ptz_preset_position_delete = "msg_dev_delete_config_ptz_preset";  /* 预置位位置删除 */
     String g_str_msg_dev_ptz_sensor_delete = "msg_dev_delete_config_sensor";                    /* 传感器删除 */

// NTP校时
     String g_str_msg_dev_ntp_set = "msg_dev_ntp_set";                    /* NTP设置 */
     String g_str_msg_dev_ntp_get = "msg_dev_ntp_get";                    /* NTP获取 */

// 拉流
     String g_str_msg_dev_start_main_stream = "msg_dev_start_main_stream";            /* 获取主码流 */
     String g_str_msg_dev_stop_main_stream  = "msg_dev_stop_main_stream";             /* 关闭主码流 */
     String g_str_msg_dev_start_sub_stream  = "msg_dev_start_sub_stream";             /* 获取子码流 */
     String g_str_msg_dev_stop_sub_stream   = "msg_dev_stop_sub_stream";              /* 关闭子码流 */

//录像
     String g_str_msg_dev_get_config_record  = "msg_dev_get_config_record";             /* 录像参数 */
     String g_str_msg_dev_set_config_record  = "msg_dev_set_config_record";             /* 录像参数 */
     String g_str_msg_dev_record_play_record  = "msg_dev_record_play_record";             /* 播放录像 */
     String g_str_msg_dev_record_stop_play_record = "msg_dev_record_stop_play_record";             /* 停止播放录像 */
     String g_str_msg_dev_record_drap = "msg_dev_record_drap";             /* 拖动 */


     String g_str_msg_dev_record_get_video_total_num="msg_dev_record_get_video_total_num";         /* 获取视频数量*/
     String g_str_msg_dev_record_get_video_list="msg_dev_record_get_video_list";         /* 获取录像列表 */
     String g_str_msg_dev_record_get_pic_list="msg_dev_record_get_pic_list";         /* 获取图片列表 */
     String g_str_msg_dev_record_get_picture="msg_dev_record_get_picture";         /* 获取图片 */
     String g_str_msg_dev_record_manual_start="msg_dev_record_manual_start";         /* 手动开始录像 */
     String g_str_msg_dev_record_get_stats="msg_dev_record_get_stats";         /* 获取录像开关状态录像 */
     String g_str_msg_dev_record_manual_stop="msg_dev_record_manual_stop";         /* 手动停止录像 */
     String g_str_msg_dev_record_del_file="msg_dev_record_del_file";                     /* 删除录像文件 */

     String g_str_msg_dev_set_config_schedule_record="msg_dev_set_config_schedule_record";                     /* 设置计划录像参数 */
     String g_str_msg_dev_get_config_schedule_record="msg_dev_get_config_schedule_record";                     /* 获取计划录像参数 */


//SD卡
     String g_str_msg_dev_record_format_sd="msg_dev_record_format_sd";                    /*格式化SD卡*/
     String g_str_msg_dev_record_get_format_status="msg_dev_record_get_format_status";                     /* 获取SD卡格式化状态 */
     String g_str_msg_dev_record_sd_status="msg_dev_record_sd_status";                     /* 获取SD是否存在 */
     String g_str_msg_dev_record_sd_info="msg_dev_record_sd_info";                     /* 获取SD信息 */


//能力级
     String g_str_msg_dev_ability_language="msg_dev_ability_language";                     /* 语言支持能力级 */
     String g_str_msg_dev_ability_audio="msg_dev_ability_audio";                     /* 声音能力级 */
     String g_str_msg_dev_ability_video="msg_dev_ability_video";                     /* 视频能力级 */
     String g_str_msg_dev_ability_record="msg_dev_ability_record";                   /* 录像能力级 */
     String g_str_msg_dev_ability_move_motion="msg_dev_ability_move_motion";             /*移动侦测能力级 */
     String g_str_msg_dev_ability_voice_motion="msg_dev_ability_voice_motion";           /* 声音侦测能力级 */
     String g_str_msg_dev_ability_ptz="msg_dev_ability_ptz";           /* 云台能力级 */
     String g_str_msg_dev_ability_sensor="msg_dev_ability_sensor";           /* 传感器能力级 */
     String g_str_msg_dev_get_command_permission="msg_dev_get_command_permission";           /* 获取命令权限能力级 */


     String g_str_msg_dev_login   = "msg_dev_login";                     /* 登陆 */

     String g_str_msg_dev_keep_alive = "msg_dev_keep_alive";


     String g_str_msg_dev_get_config_control   = "msg_dev_get_config_control";  //报警开关
     String g_str_msg_dev_set_config_control   = "msg_dev_set_config_control";  //报警开关

     String g_str_msg_dev_get_config_move_motion   = "msg_dev_get_config_move_motion";  //移动侦测
     String g_str_msg_dev_set_config_move_motion   = "msg_dev_set_config_move_motion";  //移动侦测




     String g_str_msg_dev_get_wifi_list = "msg_dev_get_wifi_list";  //获取wifi列表


     String g_str_msg_dev_set_config_network="msg_dev_set_config_network"; //网络设置



     String g_str_msg_dev_get_config_device_info="msg_dev_get_config_device_info"; //设备信息
     String g_str_msg_dev_online_update="msg_dev_online_update"; //在线升级
     String g_str_msg_dev_get_update_server_latest_version="msg_dev_get_update_server_latest_version";//获取升级信息



     String g_str_msg_dev_start_talk="msg_dev_start_talk"; //开始对讲

     String g_str_msg_dev_stop_talk="msg_dev_stop_talk"; //结束对讲

     String g_str_msg_dev_search_lan="msg_dev_search_lan"; //局域网搜索

     String g_str_msg_dev_set_config_video="msg_dev_set_config_video"; //设置视频参数
     String g_str_msg_dev_get_config_video="msg_dev_get_config_video"; //获取视频参数

     String g_str_msg_dev_set_video_rate="msg_dev_set_video_rate"; //切换实时流码率

     String g_str_msg_dev_get_watch_video_session="msg_dev_get_watch_video_session"; //获取视频在线人数

     String g_str_msg_dev_get_config_audio="msg_dev_get_config_audio";//获取音频参数
     String g_str_msg_dev_set_config_audio="msg_dev_set_config_audio";//设置音频参数
}
