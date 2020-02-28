package com.demo.dao;

import com.demo.entity.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ICourseDao {

    /*
     * 插入课程
     */
    void addCourse(@Param("courseName") String courseName, @Param("courseType") String courseType,
                   @Param("coursePro") int coursePro, @Param("courseIntro") String courseIntro,
                   @Param("courseAdd") String courseAdd);


    /*查询视频课程*/

    int queryAllWavCount();

    List<Object> queryAllWavList(@Param("startRow") int startRow, @Param("row4Page") int row4Page);

    /*按条件查询*/
    int queryWavCourseCount(@Param("courseType") String courseType);

    List<Object> queryWavList(@Param("courseType") String courseType, @Param("startRow") int startRow, @Param("row4Page") int row4Page);


    /*查询阅读课程*/

    int queryAllReadCount();

    List<Object> queryAllReadList(@Param("startRow") int startRow, @Param("row4Page") int row4Page);

    /*按条件查询*/
    int queryReadCourseCount(@Param("courseType") String courseType);

    List<Object> queryReadList(@Param("courseType") String courseType, @Param("startRow") int startRow, @Param("row4Page") int row4Page);
}
    /*List<Course> wavCourseSelect(@Param("courseType") String courseType);

    List<Course> readCourseSelect(@Param("courseType") String courseType);*/


