package com.demo.dao;

import com.demo.entity.Onlineconsultingrec;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface IDoctorSerDao {
   /*
    * 更新个人信息
    */
   void updateDocInf(@Param("doctorName") String doctorName, @Param("sex") String sex, @Param("birthday")String birthday,
                     @Param("phone") String phone, @Param("homeAdd") String homeAdd,@Param("hosId") int hosId,
                     @Param("graSchool")String graSchool,@Param("department")String department,
                     @Param("doctorId") int doctorId,@Param("headAdd") String headAdd,@Param("title") String title);

   public  List<Object> queryUserAskList(@Param("startRow") int startRow,@Param("row4Page") int row4Page,@Param("doctorId") int doctorId);

   public  int queryAllUserAskCount(int doctorId);

   public  Onlineconsultingrec queryRecById1(@Param("inquiryId") int inquiryId);

   public  void saveReplyData(int inquiryId);

   public  void updateReplyData(@Param("inquiryId") int inquiryId,@Param("replyData") String replyData);


   //韩
   List<Object> queryDocAssignList(@Param("doctorId")int doctorId, @Param("startRow")int startRow, @Param("row4Page")int row4Page);
   int queryDocAssignCount(@Param("doctorId")int doctorId);

   void updateDocMission(@Param("applyId") int applyId);

   void deleteDocMission(@Param("applyId")int applyId);

   void insertDocMission(@Param("doctorId")int doctorId, @Param("userId")int userId,@Param("recTime")String recTime,@Param("introduce")String introduce);

   List<Object> queryAllRecordList(@Param("doctorId")int doctorId, @Param("startRow")int startRow, @Param("row4Page")int row4Page);

   int queryAllRecordCount(@Param("doctorId")int doctorId);
 //  public  void updateState(int inquiryId);
}
