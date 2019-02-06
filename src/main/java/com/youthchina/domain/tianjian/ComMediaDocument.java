package com.youthchina.domain.tianjian;

import java.sql.Timestamp;

public class ComMediaDocument {
  private String docu_id;
  private String docu_local_id;
  private String docu_local_name;
  private String docu_local_format;
  private String docu_server_ali_id;
  private String docu_server_aws_id;
  private Timestamp create_time;
  private Integer is_delete;
  private Timestamp is_delete_time;

    public String getDocu_id() {
        return docu_id;
    }

    public void setDocu_id(String docu_id) {
        this.docu_id = docu_id;
    }

    public String getDocu_local_id() {
        return docu_local_id;
    }

    public void setDocu_local_id(String docu_local_id) {
        this.docu_local_id = docu_local_id;
    }

    public String getDocu_local_name() {
        return docu_local_name;
    }

    public void setDocu_local_name(String docu_local_name) {
        this.docu_local_name = docu_local_name;
    }

    public String getDocu_local_format() {
        return docu_local_format;
    }

    public void setDocu_local_format(String docu_local_format) {
        this.docu_local_format = docu_local_format;
    }

    public String getDocu_server_ali_id() {
        return docu_server_ali_id;
    }

    public void setDocu_server_ali_id(String docu_server_ali_id) {
        this.docu_server_ali_id = docu_server_ali_id;
    }

    public String getDocu_server_aws_id() {
        return docu_server_aws_id;
    }

    public void setDocu_server_aws_id(String docu_server_aws_id) {
        this.docu_server_aws_id = docu_server_aws_id;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Integer getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }

    public Timestamp getIs_delete_time() {
        return is_delete_time;
    }

    public void setIs_delete_time(Timestamp is_delete_time) {
        this.is_delete_time = is_delete_time;
    }
}
