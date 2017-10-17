package com.bw.example;

/**
 * Created by Administrator on 2017/4/18.
 */
class TestXml {

    static String getLoginXml(String username, String password) {
        return "<msg>" +
                    "<head>" +
                        "<msg_id>msg_dev_login</msg_id>" +
                    "</head>" +
                    "<body>" +
                        "<user>" + username + "</user>" +
                        "<pwd>" + password + "</pwd>" +
                    "</body>" +
                "</msg>";
    }

    static String getKeepAliveXml(String session_id) {
        return "<msg>" +
                    "<head>" +
                        "<msg_id>msg_dev_keep_alive</msg_id>" +
                        "<session_id>" + session_id + "</session_id>" +
                    "</head>" +
                "</msg>";
    }

    static String getLoginOutXml(String session_id) {
        return "<msg>" +
                    "<head>" +
                        "<msg_id>msg_dev_logout</msg_id>" +
                        "<session_id>" + session_id + "</session_id>" +
                    "</head>" +
                "</msg>";
    }

    static String getStartVideoXml(String session_id) {
        return "<msg>" +
                    "<head>" +
                        "<msg_id>msg_dev_start_main_stream</msg_id>" +
                        "<session_id>" + session_id + "</session_id>" +
                    "</head>" +
                    "<body>"+
                        "<live_stream>"+
                            "<session_id>"+session_id+"</session_id>"+
                            "<stream_type>0</stream_type>"+
                        "</live_stream>"+
                    "</body>"+
                "</msg>";
    }

    static String getStopVideoXml(String session_id,String liveStreamId) {
        return "<msg>" +
                    "<head>" +
                        "<msg_id>msg_dev_stop_main_stream</msg_id>" +
                        "<session_id>" + session_id + "</session_id>" +
                    "</head>" +
                    "<body>" +
                        "<live_stream>" +
                            "<live_stream_id>"+liveStreamId+"</live_stream_id>" +
                        "</live_stream>" +
                    "</body>" +
                "</msg>";
    }

    static String getStartPlayRecordXml(String mSessionId) {
        return "<msg>" +
                    "<head>" +
                        "<msg_id>msg_dev_record_play_record</msg_id>" +
                        "<session_id>" + mSessionId + "</session_id>" +
                    "</head>" +
                    "<body>" +
                        "<record>" +
                            "<avc_decode>1</avc_decode>" +
                            "<avc_container>0</avc_container>" +
                            "<video_name>P-vedio-20170210205335.avi</video_name>" +
                        "</record>" +
                    "</body>" +
                "</msg>";
    }

    static String getStopPlayRecordXml(String mSessionId, String playRecodeId) {
        return "<msg>" +
                    "<head>" +
                        "<msg_id>msg_dev_record_stop_play_record</msg_id>" +
                        "<session_id>" + mSessionId + "</session_id>" +
                    "</head>" +
                    "<body>" +
                        "<record>" +
                            "<play_recode_id>" + playRecodeId + "</play_recode_id>" +
                        "</record>" +
                    "</body>" +
                "</msg>";
    }

    static String getPtzCmdXml(String sessionId,int direction,
                               int motorSpeed,int motorLevel,int motorVertical) {
        return "<msg>" +
                    "<head>" +
                        "<msg_id>msg_dev_ptz_position_set</msg_id>" +
                        "<session_id>"+sessionId+"</session_id>" +
                    "</head>" +
                    "<body>" +
                        "<ptzCmd>" +
                            "<Direction>"+direction+"</Direction>" +
                            "<MotorSpeed>"+motorSpeed+"</MotorSpeed>" +
                            "<MotorLevel>"+motorLevel+"</MotorLevel>" +
                            "<MotorVertical>"+motorVertical+"</MotorVertical>" +
                        "</ptzCmd>" +
                    "</body>" +
                "</msg>";

    }

    static String getStartTalkXml(String sessionId) {
        return "<msg>" +
                    "<head>" +
                        "<msg_id>msg_dev_start_talk</msg_id>" +
                        "<session_id>"+sessionId+"</session_id>" +
                    "</head>" +
                    "<body>" +
                        "<talk>" +
                            "<playload_type>23</playload_type>" +
                            "<talk_id>"+sessionId+"</talk_id>" +
                            "<audio_sample_rate>8000</audio_sample_rate>" +
                            "<audio_bit_width>16</audio_bit_width>" +
                        "</talk>" +
                    "</body>" +
                "</msg>";
    }

    static String getStopTalkXml(String sessionId) {
        return "<msg>" +
                "<head>" +
                "<msg_id>msg_dev_stop_talk</msg_id>" +
                "<session_id>"+sessionId+"</session_id>" +
                "</head>" +
                "<body>" +
                "<talk>" +
                "<talk_id>"+sessionId+"</talk_id>" +
                "</talk>" +
                "</body>" +
                "</msg>";
    }

}
