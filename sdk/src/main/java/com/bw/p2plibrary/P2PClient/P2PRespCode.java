package com.bw.p2plibrary.P2PClient;

/**
 * Created by Administrator on 2017/1/6.
 */

public interface P2PRespCode
{
    //通用错误代码
      int	ERROR_MSG_COMMON_SUCCESS	=	0	;	//	设置成功
      int	ERROR_MSG_COMMON_TIMEOUT	=	1	;	//	等待超时
      int	ERROR_MSG_COMMON_FORMAT_ERROR	=	2	;	//	设置信息不对，请开发人员检测自己的下发数据格式
      int	ERROR_MSG_COMMON_PARAM_EMPTY	=	3	;	//	设置的数据为空
      int	ERROR_MSG_COMMON_PARAM_ERROR	=	4	;	//	设置的数据错误
      int	ERROR_MSG_COMMON_OPERATION_FAILED	=	5	;	//	操作失败
      int	ERROR_MSG_COMMON_SYSTEM_EXCEPTION	=	6	;	//	系统内部异常
      int	ERROR_MSG_COMMON_CONFIG_NOT_EXIST	=	7	;	//	配置文件不存在
      int	ERROR_MSG_COMMON_CONFIG_ILLEGAL	=	8	;	//	非法操作
      int   ERROR_MSG_COMMON_ACTION_DISABLE  = 9;    //该功能未开启使能
      int   ERROR_MSG_COMMON_LOAD_PARAM_ERROR  = 10 ; //加载参数失败
      int   ERROR_MSG_COMMON_SET_PARAM_ERROR = 11;   //设置参数失败
      int   ERROR_MSG_COMMON_SAVE_PARAM_ERROR  = 12 ; //保存参数失败
    //==============================================================================================

    //语言设置
      int	ERROR_MSG_SET_LANGUAGE_LANGUAGE	=	10000	;	//	暂时不支持该语言设置!
    //==============================================================================================

    //所有侦测总开关
      int	ERROR_MSG_SET_CONTROL_ALLALARM	=	10100	;	//	侦测总开关设置错误
    //==============================================================================================

    //rtsp设置
      int	ERROR_MSG_SET_RTSP_PORT	=	10200	;	//	rtsp端口不符合
    //==============================================================================================

    //音频配置
      int	ERROR_MSG_SET_AUDIO_ADEVMICENABLE	=	10300	;	//	MIC使能开关错误
      int	ERROR_MSG_SET_AUDIO_ADEVICEMIC	=	10301	;	//	设置的MIC增益等级不在1~10的范围内
      int	ERROR_MSG_SET_AUDIO_ADEVSPEEANBALE	=	10302	;	//	喇叭的使能开关错误
      int	ERROR_MSG_SET_AUDIO_ADEVICESPEAKER	=	10303	;	//	设置的喇叭播放的音量不在1~10的范围内
      int	ERROR_MSG_SET_AUDIO_ABAUDRATE	=	10304	;	//	采样率设置错误
      int	ERROR_MSG_SET_AUDIO_AENCTYPE	=	10305	;	//	音频格式设置错误
      int	ERROR_MSG_SET_AUDIO_ABITWITCH	=	10306	;	//	音频位宽设置错误
      int	ERROR_MSG_SET_AUDIO_APERFRAMEADOPT	=	10307	;	//	帧采样率设置错误
    //==============================================================================================

    //视频设置
      int	ERROR_MSG_SET_VEDIO_USENSORTYPE	=	10400	;	//	sensor的类型选择错误
      int	ERROR_MSG_SET_VEDIO_USTANDARD	=	10401	;	//	视频制式选择错误
      int	ERROR_MSG_SET_VEDIO_USETMIRROR	=	10402	;	//	镜像使能设置错误
      int	ERROR_MSG_SET_VEDIO_USETFLIP	=	10403	;	//	翻转使能设置错误
      int	ERROR_MSG_SET_VEDIO_USETFLASHHZ	=	10404	;	//	抗闪频率设置错误
      int	ERROR_MSG_SET_VEDIO_MAIN_STREAM_MSTREAMTYPE	=	10405	;	//	主码流码流的类型设置错误
      int	ERROR_MSG_SET_VEDIO_MAIN_STREAM_MRESOLUTION	=	10406	;	//	主码流图像的分辨率设置错误
      int	ERROR_MSG_SET_VEDIO_MAIN_STREAM_MFRAMERATE	=	10407	;	//	主码流图像的设置帧率不在1~30的范围内
      int	ERROR_MSG_SET_VEDIO_MAIN_STREAM_MQUALITYBR	=	10408	;	//	主码流固定码率设置错误
      int	ERROR_MSG_SET_VEDIO_SUB_STREAM_MSTREAMTYPE	=	10409	;	//	子码流码流的类型设置错误
      int	ERROR_MSG_SET_VEDIO_SUB_STREAM_MRESOLUTION	=	10410	;	//	子码流图像的分辨率设置错误
      int	ERROR_MSG_SET_VEDIO_SUB_STREAM_MFRAMERATE	=	10411	;	//	子码流图像的设置帧率不在1~30的范围内
      int	ERROR_MSG_SET_VEDIO_SUB_STREAM_MQUALITYBR	=	10412	;	//	子码流固定码率设置错误
      int	ERROR_MSG_SET_ISP_USETBRIGHTNESS	=	10500	;	//	ISP亮度设置不在0~255的范围内
      int	ERROR_MSG_SET_ISP_USETCONTRAST	=	10501	;	//	ISP对比度不在0~255的范围内
      int	ERROR_MSG_SET_ISP_USETHUE	=	10502	;	//	ISP色度不在-127~128的范围内
      int	ERROR_MSG_SET_ISP_USETSATURATION	=	10503	;	//	ISP亮度设置不在0~255的范围内
      int	ERROR_MSG_SET_RECONRD_RECORDENABLE	=	10600	;	//	录像使能设置错误
      int	ERROR_MSG_SET_RECONRD_RMANURECENABLE	=	10601	;	//	手动录像使能设置错误
      int	ERROR_MSG_SET_RECONRD_RCOVERENABLE	=	10602	;	//	SD卡满时自动覆盖设置错误
      int	ERROR_MSG_SET_RECONRD_RECORDCHANNAL	=	10603	;	//	码流设置错误
      int	ERROR_MSG_SET_RECONRD_RECORDFORMAT	=	10604	;	//	格式选择错误
      int	ERROR_MSG_SET_RECONRD_RPERRECORDTIME	=	10605	;	//	预录像时间设置错误
      int	ERROR_MSG_SET_RECONRD_RPREENABLE	=	10606	;	//	预录像使能错误
      int	ERROR_MSG_SET_RECONRD_RPRETIME	=	10607	;	//	预录像时间设置错误
      int	ERROR_MSG_SET_RECONRD_RFILEPATHFORMAT	=	10608	;	//	录像路径格式选择错误
      int	ERROR_MSG_SET_RECONRD_RFILEFORMAT	=	10609	;	//	录像时间格式选择错误
    //==============================================================================================

    //网络设置
      int	ERROR_MSG_SET_NETWORK_WIRED_LAN_EDHCP	=	10700	;	//	有线网络DHCP设置错误
      int	ERROR_MSG_SET_NETWORK_WIRED_LAN_ESTATICIP	=	10701	;	//	有线网络IP非法
      int	ERROR_MSG_SET_NETWORK_WIRED_LAN_ENETMASK	=	10702	;	//	有线网络子网掩码非法
      int	ERROR_MSG_SET_NETWORK_WIRED_LAN_EGATEWAY	=	10703	;	//	有线网络网关非法
      int	ERROR_MSG_SET_NETWORK_WIRED_LAN_EDNS1	=	10704	;	//	有线网络DNS1无效
      int	ERROR_MSG_SET_NETWORK_WIRED_LAN_EDNS2	=	10705	;	//	有线网络DNS2无效
      int	ERROR_MSG_SET_NETWORK_WIRELESS_LAN_WDHCP	=	10706	;	//	无线网络DHCP设置错误
      int	ERROR_MSG_SET_NETWORK_WIRELESS_LAN_SMARTLINKENABLE	=	10707	;	//	SmartLink使能错误
      int	ERROR_MSG_SET_NETWORK_WIRELESS_LAN_QRCODEENABLE	=	10708	;	//	QrCode使能错误
      int	ERROR_MSG_SET_NETWORK_WIRELESS_LAN_SOUNDWAVEENABLE	=	10709	;	//	声波使能错误
      int	ERROR_MSG_SET_NETWORK_WIRELESS_LAN_WSTATICIP	=	10710	;	//	无线网络IP设置非法
      int	ERROR_MSG_SET_NETWORK_WIRELESS_LAN_WNETMASK	=	10711	;	//	无线网络子网掩码非法
      int	ERROR_MSG_SET_NETWORK_WIRELESS_LAN_WGATEWAY	=	10712	;	//	无线网络网关非法
      int	ERROR_MSG_SET_NETWORK_WIRELESS_LAN_WMODE	=	10713	;	//	WIFI连接模式设置错误
      int	ERROR_MSG_SET_NETWORK_WIRELESS_LAN_WAUTHEN	=	10714	;	//	WIFI验证模式选择错误
      int	ERROR_MSG_SET_NETWORK_WIRELESS_LAN_WENCPTYPE	=	10715	;	//	WIFI安全模式选择错误
      int	ERROR_MSG_SET_NETWORK_WIRELESS_LAN_WDEFKEYID	=	10716	;	//	WIFI中KEY值选择错误
      int	ERROR_MSG_SET_NETWORK_WIRELESS_LAN_WSTASSIDENABLE	=	10717	;	//	STA使能设置错误
      int	ERROR_MSG_SET_NETWORK_WIRELESS_LAN_WSTASSIDENABLE____________________________1	=	10718	;	//	STA模式下设置SSID错误
      int	ERROR_MSG_SET_NETWORK_WIRELESS_LAN_WSTAPASSWORD	=	10719	;	//	STA模式下设置密码错误
      int	ERROR_MSG_SET_NETWORK_WIRELESS_LAN_WAPSSID	=	10720	;	//	AP模式下设置SSID错误
    //==============================================================================================

    //移动侦测
      int	ERROR_MSG_SET_MOVE_MOTION_MDETECT	=	10800	;	//	移动侦测使能错误
      int	ERROR_MSG_SET_MOVE_MOTION_MDETECTLINK	=	10801	;	//	移动侦测联动选择错误
      int	ERROR_MSG_SET_MOVE_MOTION_MDETECTSEN	=	10802	;	//	移动侦测的敏感度不在1~255的范围内
      int	ERROR_MSG_SET_MOVE_MOTION_MDETECTINTERTIME	=	10803	;	//	移动侦测触发的间隔时间不在1~300S范围内
      int	ERROR_MSG_SET_VOICE_MOTION_ADETECT	=	10900	;	//	声音侦测使能非法
      int	ERROR_MSG_SET_VOICE_MOTION_ADETECTLINKI	=	10901	;	//	声音侦测联动报警选择错误
      int	ERROR_MSG_SET_VOICE_MOTION_ADETECTSEN	=	10902	;	//	声音侦测使能非法
      int	ERROR_MSG_SET_VOICE_MOTION_ADETECTINTERTIME	=	10903	;	//	声音侦测触发的间隔时间不在1~300S范围内
    //==============================================================================================

    //抓拍
      int	ERROR_MSG_SET_SNOPSHAT_PSNAPSHOTENABLE	=	11000	;	//	抓拍使能设置错误
      int	ERROR_MSG_SET_SNOPSHAT_PCOVERENABLE	=	11001	;	//	抓拍覆盖使能设置错误
      int	ERROR_MSG_SET_SNOPSHAT_PSNAPFILENAME	=	11002	;	//	抓拍的文件名设置错误
      int	ERROR_MSG_SET_SNOPSHAT_PSNAPINTER	=	11003	;	//	抓拍的时间间隔不在1~3S范围内
      int	ERROR_MSG_SET_SNOPSHAT_PSNAPTRIGGERINTER	=	11004	;	//	两次触发的时间间隔设置不在1~30的范围内
      int	ERROR_MSG_SET_SNOPSHAT_PSNAPPICNUM	=	11005	;	//	每次触发抓拍图片的张数设置不在1~6张
    //==============================================================================================

    //NTP校时
      int	ERROR_MSG_SET_NTP_NNTPENABLE	=	11200	;	//	ntp使能设置错误
      int	ERROR_MSG_SET_NTP_NTIMINGWAY	=	11201	;	//	NTP校时选择错误
      int	ERROR_MSG_SET_NTP_NTIMINGINTER	=	11202	;	//	ntp循环校时的时间不在10~120min的范围内
      int	ERROR_MSG_SET_NTP_NNTPSERVER	=	11203	;	//	ntp校时的服务器非法
      int	ERROR_MSG_SET_NTP_NNTPPORT	=	11204	;	//	ntp端口不在1~65535的范围内
      int	ERROR_MSG_SET_NTP_NNTPTIMEZONE	=	11205	;	//	NTP时区设置错误
    //==============================================================================================

    //email配置
      int	ERROR_MSG_SET_EMAIL_EMAILENABLE	=	11300	;	//	Email使能设置错误
    //==============================================================================================

    //云台设置
      int	ERROR_MSG_SET_PTZ_PTZENABLE	=	11400	;	//	云台转动使能设置错误
      int	ERROR_MSG_SET_PTZ_PCRUISEENABLE	=	11401	;	//	云台巡航使能设置错误
      int	ERROR_MSG_SET_PTZ_POSITION_MAX	=	11402	;	//	云台位置最大值错误
      int	ERROR_MSG_SET_PTZ_POSITION_NOW	=	11403	;	//	云台当前位置错误
      int	ERROR_MSG_SET_PTZ_PPRESETSTATIME	=	11404	;	//	每个预置位停留时间设置没有在5~60min的范围内
      int	ERROR_MSG_SET_PTZ_PDEFPRESET	=	11405	;	//	默认预置位使能设置错误
      int	ERROR_MSG_SET_PTZ_PTZSPEED	=	11406	;	//	云台转动的速度等级设置不在0~5范围内
      int	ERROR_MSG_SET_PTZ_PTZLEFTRIGHTREV	=	11407	;	//	云台左右反向使能错误
      int	ERROR_MSG_SET_PTZ_PTZUPDOWNREV	=	11408	;	//	云台上下反向使能错误
      int	ERROR_MSG_SET_PTZ_PTZPRESETNUMBER	=	11409	;	//	云台预置位个数没有在0~10的范围内
      int	ERROR_MSG_SET_PTZ_PRESET_ALL_ENABLE	=	11410	;	//	所有预置位使能设置错误
      int	ERROR_MSG_SET_PTZ_PRESET_ENABLE	=	11411	;	//	预置位使能设置错误
      int	ERROR_MSG_SET_PTZ_PRESET_ID	=	11412	;	//	预置位ID设置错误
      int	ERROR_MSG_SET_PTZ_PTZPRESETOVERRANGE	=	11413	;	//	云台预置位超过范围[水平：0~4097；垂直：0~1750]
    //==============================================================================================

    //IPC用户登录名和密码
      int	ERROR_MSG_SET_IPC_USER_IPCAMNUMMAX	=	11500	;	//	设置的最大用户数量不在1~10的范围内
      int	ERROR_MSG_SET_IPC_USER_ID	=	11501	;	//	用户ID错误
      int	ERROR_MSG_SET_IPC_USER_IPCAMUSER	=	11502	;	//	用户名格式不对
      int	ERROR_MSG_SET_IPC_USER_IPCAMPASSWORD	=	11503	;	//	用户名密码格式不对
      int	ERROR_MSG_SET_IPC_USER_IPCAMPERMISSION	=	11504	;	//	没有该用户等级
      int	ERROR_MSG_SET_IPC_USER_NAME_EXISTENT	=	11505	;	//	用户名已经存在
      int	ERROR_MSG_SET_IPC_USER_MAX	=	11506	;	//	用户数量已达最大值
    //==============================================================================================

    //传感器设置
      int	ERROR_MSG_SET_SENSOR_ALLENABLE	=	12400	;	//	所有传感器使能设置错误
      int	ERROR_MSG_SET_SENSOR_ENABLE	=	12401	;	//	传感器使能设置错误
      int	ERROR_MSG_SET_SENSOR_ID	=	12402	;	//	传感器的ID号设置错误
      int	ERROR_MSG_SET_SENSOR_RENAME	=	12403	;	//	传感器的重命名错误
      int	ERROR_MSG_SET_SENSOR_PRESET_ALL_ENABLE	=	12404	;	//	所有传感器/预置位绑定使能错误
      int	ERROR_MSG_SET_SENSOR_PRESET_ID	=	12405	;	//	传感器/预置位绑定ID错误
      int	ERROR_MSG_SET_SENSOR_PRESET_ENABLE	=	12406	;	//	传感器/预置位绑定使能错误
    //==============================================================================================


      int	ERROR_MSG_SET_IPC_FIREWALL_FIREWALLENABLE	=	11600	;	//	IPC防火墙使能设置错误
      int	ERROR_MSG_SET_IPC_FIREWALL_FITERRULE	=	11601	;	//	IPC防火墙使能翻转的功能设置错误
      int	ERROR_MSG_SET_IPC_FIREWALL_FITERIP	=	11602	;	//	IPC防火墙IP设置非法
      int	ERROR_MSG_SET_FTP_FTPENABLE	=	11700	;	//	Ftp使能设置错误
      int	ERROR_MSG_SET_FTP_FTPMODE	=	11701	;	//	Ftp设置模式错误
      int	ERROR_MSG_SET_FTP_FTPSERVER	=	11702	;	//	Ftp服务器设置非法
      int	ERROR_MSG_SET_FTP_FTPPORT	=	11703	;	//	Ftp设置的端口不在1~65535的范围内
      int	ERROR_MSG_SET_FTP_FTPUSER	=	11704	;	//	Ftp用户输入错误
      int	ERROR_MSG_SET_FTP_FTPPASSWORD	=	11705	;	//	Ftp密码格式不对
      int	ERROR_MSG_SET_FTP_FTPDIR	=	11706	;	//	Ftp上传目录设置错误
      int	ERROR_MSG_SET_OSD_OSDMAINSHOWENABLE	=	11800	;	//	主码流OSD显示时间使能错误
      int	ERROR_MSG_SET_OSD_OSDMAINSHOWSTR	=	11801	;	//	主码流显示公司LOGO地址非法
      int	ERROR_MSG_SET_OSD_OSDMAINTIMEFORMAT	=	11802	;	//	主码流OSD时间格式设置非法
      int	ERROR_MSG_SET_OSD_OSDSUBSHOWENABLE	=	11803	;	//	子码流显示时间使能错误
      int	ERROR_MSG_SET_OSD_OSDSUBSHOWSTR	=	11804	;	//	子码流显示公司LOGO地址非法
      int	ERROR_MSG_SET_OSD_OSDSUBTIMEFORMAT	=	11805	;	//	子码流OSD时间格式设置非法
      int	ERROR_MSG_SET_SCHEDULE_RECORD_ASCHEDULEENABLE	=	11900	;	//	子计划录像使能设置错误
      int	ERROR_MSG_SET_SCHEDULE_RECORD_AWEEKVALIDDATE	=	11901	;	//	计划录像周数使能错误
      int	ERROR_MSG_SET_RTMP_MASTER_SERVER_VIDEOENABLE	=	12000	;	//	RTMP服务器主码流视频使能错误
      int	ERROR_MSG_SET_RTMP_MASTER_SERVER_AUDIOENABLE	=	12001	;	//	RTMP服务器主码流音频使能错误
      int	ERROR_MSG_SET_RTMP_MASTER_SERVER_MAINSTREAMURL	=	12002	;	//	RTMP服务器主码流URL地址错误
      int	ERROR_MSG_SET_RTMP_MASTER_SERVER_SUBSTREAMURL	=	12003	;	//	RTMP服务器主码流URL名错误
      int	ERROR_MSG_SET_RTMP_MASTER_SERVER_SUBSTREAMNAME	=	12004	;	//	RTMP服务器子码流URL名错误
      int	ERROR_MSG_SET_RTMP_STANDBY_SERVER_VIDEOENABLE	=	12005	;	//	RTMP备用服务器主码流视频使能错误
      int	ERROR_MSG_SET_RTMP_STANDBY_SERVER_AUDIOENABLE	=	12006	;	//	RTMP备用服务器主码流音频使能错误
      int	ERROR_MSG_SET_RTMP_STANDBY_SERVER_MAINSTREAMURL	=	12007	;	//	RTMP备用服务器主码流URL地址错误
      int	ERROR_MSG_SET_RTMP_STANDBY_SERVER_MAINSTREAMNAME	=	12008	;	//	RTMP备用服务器主码流URL名错误
      int	ERROR_MSG_SET_RTMP_STANDBY_SERVER_SUBSTREAMURL	=	12009	;	//	RTMP备用服务器子码流URL地址错误
      int	ERROR_MSG_SET_RTMP_STANDBY_SERVER_SUBSTREAMNAME	=	12010	;	//	RTMP备用服务器子码流URL名错误
      int	ERROR_MSG_SET_RTMP_STANDBY_SERVER_SUBSTREAMNAME____________________1	=	12011	;	//	RTMP备用服务器子码流URL名错误
      int	ERROR_MSG_SET_RTMP_RTMP_SCHEDULE_STANDBY_ENABLE	=	12012	;	//	RTMP计划录像使能错误
      int	ERROR_MSG_SET_RTMP_RTMP_SCHEDULE_STANDBY_WEEKDAY1	=	12013	;	//	RTMP周一录像使能设置错误
      int	ERROR_MSG_SET_RTMP_RTMP_SCHEDULE_STANDBY_WEEKDAY2	=	12014	;	//	RTMP周二录像使能设置错误
      int	ERROR_MSG_SET_RTMP_RTMP_SCHEDULE_STANDBY_WEEKDAY3	=	12015	;	//	RTMP周三录像使能设置错误
      int	ERROR_MSG_SET_RTMP_RTMP_SCHEDULE_STANDBY_WEEKDAY4	=	12016	;	//	RTMP周四录像使能设置错误
      int	ERROR_MSG_SET_RTMP_RTMP_SCHEDULE_STANDBY_WEEKDAY5	=	12017	;	//	RTMP周五录像使能设置错误
      int	ERROR_MSG_SET_RTMP_RTMP_SCHEDULE_STANDBY_WEEKDAY6	=	12018	;	//	RTMP周六录像使能设置错误
      int	ERROR_MSG_SET_RTMP_RTMP_SCHEDULE_STANDBY_WEEKDAY7	=	12019	;	//	RTMP周日录像使能设置错误
      int	ERROR_MSG_SET_SDCARD_SDLOSTENABLE	=	12100	;	//	SD丢失报警使能错误
      int	ERROR_MSG_SET_SDCARD_SDSPACEENABLE	=	12101	;	//	SD自动覆盖使能设置错误
      int	ERROR_MSG_SET_LED_STATUS_LEDENABLE	=	12200	;	//	LED使能设置错误
      int	ERROR_MSG_SET_LED_STATUS_LEDFLASHSTATUS	=	12201	;	//	LED状态使能错误
      int	ERROR_MSG_SET_IRCUT_IRCUTREVEENABLE	=	12300	;	//	Ircut反向使能错误
      int	ERROR_MSG_SET_IRCUT_IRCUTMANUALCTL	=	12301	;	//	ircut手动控制使能错误
      int	ERROR_MSG_SET_IRCUT_IRCUTSWITCHDELAY	=	12302	;	//	irCut切换延时时间错误


      int	ERROR_MSG_SET_SENSOR_SWINDOORENABLE	=	12401	;	//	门窗传感器使能设置错误
      int	ERROR_MSG_SET_SENSOR_SWINDOOR	=	12402	;	//	门窗传感器的ID号设置错误
      int	ERROR_MSG_SET_SENSOR_SWINDOORRENAME	=	12403	;	//	门窗传感器的ID号重命名失败
      int	ERROR_MSG_SET_SENSOR_SWINDOORPRESET	=	12404	;	//	门窗传感器的ID号预置位错误
      int	ERROR_MSG_SET_SENSOR_SBODYINDUCENABLE	=	12405	;	//	人体感应使能设置错误
      int	ERROR_MSG_SET_SENSOR_SBODYINDUC	=	12406	;	//	人体感应传感器ID设置错误
      int	ERROR_MSG_SET_SENSOR_SBODYINDUCENAME	=	12407	;	//	人体感应传感器的ID号重命名错误
      int	ERROR_MSG_SET_SENSOR_SBODYINDUCPRESET	=	12408	;	//	人体感应传感器ID预置位错误
      int	ERROR_MSG_SET_SENSOR_SSMOKEINDUCENABLE	=	12409	;	//	烟雾报警传感器使能设置错误
      int	ERROR_MSG_SET_SENSOR_SSMOKEINDUC	=	12410	;	//	烟雾报警传感器ID设置错误
      int	ERROR_MSG_SET_SENSOR_SSMOKEINDUCENAME	=	12411	;	//	烟雾报警传感器的ID号重命名错误
      int	ERROR_MSG_SET_SENSOR_SSMOKEINDUCPRESET	=	12412	;	//	烟雾报警传感器ID预置位错误
      int	ERROR_MSG_SET_SENSOR_SGASINDUCENABLE	=	12413	;	//	瓦斯侦测使能设置错误
      int	ERROR_MSG_SET_SENSOR_SGASINDUC	=	12414	;	//	瓦斯侦测传感器ID设置错误
      int	ERROR_MSG_SET_SENSOR_SGASINDUCENAME	=	12415	;	//	瓦斯侦测传感器的ID号重命名错误
      int	ERROR_MSG_SET_SENSOR_SGASINDUCPRESET	=	12416	;	//	瓦斯侦测传感器ID预置位错误





      int	ERROR_MSG_SET_SIP_SSERVERADDR	=	12500	;	//	SIP服务器非法
      int	ERROR_MSG_SET_SIP_SSERVERPORT	=	12501	;	//	SIP府服务器端口错误
      int	ERROR_MSG_SET_SIP_SLOGINPASSWD	=	12502	;	//	SIP登陆密码非法
      int	ERROR_MSG_SET_SIP_SLOGINUSER	=	12503	;	//	SIP登陆用户格式不对
      int	ERROR_MSG_SET_TIMEZONE_TIME_TIMEZONE	=	12600	;	//	时区设置错误
      int	ERROR_MSG_SET_TIMEZONE_TIME_LOCALTIME_FORMAT	=	12601	;	//	本地时间格式设置错误
      int	ERROR_MSG_SET_UPDATE_SERVER_UPDATESERVERADDR	=	12700	;	//	更新服务器地址非法
      int	ERROR_MSG_SET_FACTORY_DEFAULT_RFACTORYDEFLEVEL	=	12800	;	//	更新服务器文件名非法
      int	ERROR_MSG_SET_DISCOVERYMODE	=	13000	;	//	设置ONVIF搜索模式错误

      int	ERROR_MSG_STORAGE_SD_NOT_EXIST	=	16000	;	//	SD卡不存在
      int	ERROR_MSG_STORAGE_SD_CAPATICY_NOT_ENOUGH	=	16001	;	//	SD卡容量不够
      int	ERROR_MSG_STORAGE_SD_GET_INFO	=	16002	;	//	SD卡获取信息失败
      int	ERROR_MSG_STORAGE_SD_FORMAT	=	16003	;	//	SD卡格式化失败
      int	ERROR_MSG_STORAGE_SD_FORMATING	=	16004	;	//	SD卡正在格式化
      int	ERROR_MSG_STORAGE_IS_RECORDING	=	16005	;	//	当前正在录像
      int	ERROR_MSG_STORAGE_RECORD_SWITCH_CLOSE	=	16006	;	//	您已经关闭了录像开关
      int	ERROR_MSG_STORAGE_MANUAL_RECORD_SWITCH_CLOSE	=	16007	;	//	您关闭了手动录像开关
      int	ERROR_MSG_STORAGE_NOT_RECORDING	=	16008	;	//	现在没有录像
      int	ERROR_MSG_STORAGE_NOT_FIND_FILE	=	16009	;	//	没有找到列表文件
      int	ERROR_MSG_STORAGE_FILE_READ_ERROR	=	16010	;	//	文件读取错误
      int	ERROR_MSG_STORAGE_PICTURE_NOT_EXIST	=	16011	;	//	该图片不存在
      int	ERROR_MSG_STORAGE_PICTURE_READ_FAIL	=	16012	;	//	读取图片文件失败
      int	ERROR_MSG_STORAGE_VIDEO_NOT_EXIST	=	16013	;	//	该视频不存在
      int	ERROR_MSG_STORAGE_VIDEO_READ_FAIL	=	16014	;	//	读取录像文件失败
      int	ERROR_MSG_STORAGE_NOT_VIDEO_SHOW	=	16015	;	//	当前没有看录像
      int	ERROR_MSG_STORAGE_LIST_IS_NULL	=	16016	;	//	获取的列表为空
      int	ERROR_MSG_STORAGE_SESSION_MAX	=	16017	;	//	当前观看人数过多，请稍后再试

      int	ERROR_MSG_CODEC_LOAD_OSD	=	18000	;	//	加载OSD显示图片失败
      int	ERROR_MSG_CODEC_CREATE_OSD_PIC	=	18001	;	//	没有生成OSD显示图片
      int	ERROR_MSG_CODEC_SIZE_ILLEGAL	=	18002	;	//	图片尺寸不合法，可能会影响OSD反色功能
      int	ERROR_MSG_CODEC_POSITION_CONFLICT	=	18003	;	//	显示位置产生冲突
      int	ERROR_MSG_CODEC_SET_IPS	=	18004	;	//	设置ISP失败
      int	ERROR_MSG_CODEC_SNAPSHOT	=	18005	;	//	抓拍生成图片失败
      int	ERROR_MSG_CODEC_SNAPSHOT_ENABLE_CLOSE	=	18006	;	//	抓拍使能开关关闭
      int	ERROR_MSG_CODEC_MOTION_DETECTION_OPEN	=	18007	;	//	移动侦测已经开启
      int	ERROR_MSG_CODEC_MOTION_DETECTION_CLOSE	=	18008	;	//	移动侦测已经关闭
      int	ERROR_MSG_CODEC_SOUND_DETECTION_OPEN	=	18009	;	//	声音侦已经开启
      int	ERROR_MSG_CODEC_SOUND_DETECTION_CLOSE	=	18010	;	//	声音侦测已经关闭
      int	ERROR_MSG_CODEC_SET_ROTATE	=	18011	;	//	设置图像翻转失败
      int	ERROR_MSG_CODEC_SET_ANTI_FLICKER	=	18012	;	//	设置抗闪频率失败


    //云台控制 已更新
      int	ERROR_MSG_FCMISC_PTZ_PRESETBINDISFULL	=	19000;	//	预置位绑定的传感器设备已满9台
      int	ERROR_MSG_FCMISC_PTZ_POSITIONISMAX	=	19001	;	//	云台位置已达最大位置
      int	ERROR_MSG_FCMISC_PTZ_PRESET_MAX	=	19002	;	//	预置位数目已达最大值
    //=======================================================================================================

    //传感器控制
      int    ERROR_MSG_FCMISC_SENSOR_BINDORUNBIND =	19100;	//	传感器绑定或解绑错误
      int	ERROR_MSG_FCMISC_SENSOR_BINDALREADY	=	19101	;	//	传感器已经被绑定
      int    ERROR_MSG_FCMISC_SENSOR_REGISTERALREADY =	19102	;	//	传感器已经注册
      int	ERROR_MSG_FCMISC_SENSOR_SAVEALREADY	=	19103	;	//	此处已有传感器注册
      int	ERROR_MSG_FCMISC_SENSOR_NOTFIND	=	19104	;	//	没有发现传感器
      int	ERROR_MSG_FCMISC_SENSOR_NOTMAKEOUT	=	19105	;	//	不能分辨传感器
      int	ERROR_MSG_FCMISC_SENSOR_MAX	=	19106	;	//	传感器数目已达最大值
    //=======================================================================================================

    //用户登录
      int    ERROR_MSG_SDKSERVER_IPC_USER_LOGIN_SUCCESS =	0	;	//	登陆成功
      int    ERROR_MSG_SDKSERVER_IPC_USER_LOGIN_USER_NAME_INEXISTENT =	20001	;	//	用户名不存在
      int    ERROR_MSG_SDKSERVER_IPC_USER_LOGIN_USER_PASSWORD_ERROR =	20002	;	//	密码错误
      int	ERROR_MSG_SDKSERVER_IPC_USER_INVALID_MSG_ID	=	20003	;	//	无效的消息ID
      int	ERROR_MSG_SDKSERVER_IPC_USER_INVALID_SESSION	=	20004	;	//	无效的会话
      int	ERROR_MSG_SDKSERVER_IPC_USER_NO_PERMISSIONS	=	20005	;	//	无此权限
      int	ERROR_MSG_SDKSERVER_IPC_USER_LOGOUT_SUCCESS	=	20006	;	//	注销成功
      int	ERROR_MSG_SDKSERVER_IPC_USER_LOGOUT_FALSE	=	20007	;	//	注销失败
      int	ERROR_MSG_SDKSERVER_IPC_USER_NO_LICENSE	=	20008	;	//	没有登陆
      int	ERROR_MSG_SDKSERVER_IPC_USER_PARSE_CMD_ERROR	=	20009	;	//	解析命令错误，请检查用户权限管理文件
      int	ERROR_MSG_SDKSERVER_IPC_USER_LOGIN_ALREADY	=	20010	;	//	用户已经登录
    //=======================================================================================================


  int	ERROR_MSG_UPGRADE_ERR_NULL_PTR	=	21000;//	输入参数空指针错误
  int	ERROR_MSG_UPGRADE_ERR_FILE_NOT_EXIST	=	21001;//	文件不存在
  int	ERROR_MSG_UPGRADE_ERR_MEM_NOT_ENOUGH	=	21002;//	可用内存不足
  int	ERROR_MSG_UPGRADE_ERR_DECRYTION_ABNORMALLY	=	21003;//	openssl解密异常
  int	ERROR_MSG_UPGRADE_ERR_DECRYTION_FAILD	=	21004;//	openssl解密失败
  int	ERROR_MSG_UPGRADE_ERR_TAR_ABNORMALLY	=	21005;//	tar解压异常
  int	ERROR_MSG_UPGRADE_ERR_TAR_FAILED	=	21006;//	tar解压失败
  int	ERROR_MSG_UPGRADE_ERR_MD5SUM_ABNORMALLY	=	21007;//	计算md5异常
  int	ERROR_MSG_UPGRADE_ERR_MD5SUM_FAILD	=	21008;//	MD5结果错误
  int	ERROR_MSG_UPGRADE_ERR_UMOUNT_FAILED	=	21009;//	umount失败
  int	ERROR_MSG_UPGRADE_ERR_FLASHCP_ERR	=	21010;//	FLASHCP失败
  int	ERROR_MSG_UPGRADE_ERR_SER_CONFIG	=	21011;//	服务器配置文件错误
  int	ERROR_MSG_UPGRADE_ERR_SD_ERR	=	21012;//	sd卡错误
  int	ERROR_MSG_UPGRADE_ERR_GET_CMD	=	21013;//	获取指令参数失败
  int	ERROR_MSG_UPGRADE_ERR_HTTP_INIT	=	21014;//	网络参数验证错误
  int	ERROR_MSG_UPGRADE_ERR_DOWN_UPDATE_FILE	=	21015;//	下载升级文件失败
  int	ERROR_MSG_UPGRADE_ERR_GET_UPDATE_CONFIG_FILE	=	21016;//	获取升级配置文件信息失败
  int	ERROR_MSG_UPGRADE_ERR_SET_UPDATE_CONFIG_FILE	=	21017;//	设置配置文件失败
  int	ERROR_MSG_UPGRADE_ERR_SET_CMD_NOT_MATCHING	=	21018;//	客户端命令与服务器命令不一致
  int	ERROR_MSG_UPGRADE_ERR_TYPE_VERSION_NOT_MATCHING	=	21019;//	设备类型不一致或软件版本相同
  int	ERROR_MSG_UPGRADE_ERR_DEFAULLT_CONFIG	=	21020;//	升级个人配置文件失败
  int	ERROR_MSG_UPGRADE_ERR_RM_FILE	=	21021;//	rm文件失败
  int	ERROR_MSG_UPGRADE_ERR_REBOOT_PROCESS	=	21022;//	重启进程失败，即将重启机器
  int	ERROR_MSG_UPGRADE_ERR_HTTP_NET_NOT_REACH	=	21023;//	网络不通，终止升级
  int	ERROR_MSG_UPGRADE_ERR_HTTP_NET_SLOW	=	21024;//	网速很慢，终止升级
  int	ERROR_MSG_UPGRADE_ERR_CP_ERR	=	21025;//	CP文件失败
  int	ERROR_MSG_UPGRADE_ERR_SHELL_ERR	=	21026;//	shell脚本执行失败
  int	ERROR_MSG_UPGRADE_UPDATE_UPDATE_PROCEED                   	=	21027;//	正在进行升级，请稍等




}
