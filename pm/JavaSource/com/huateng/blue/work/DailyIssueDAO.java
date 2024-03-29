package com.huateng.blue.work;

import com.huateng.blue.work.DailyIssue;
import com.huateng.blue.work.DailyIssueExample;
import java.util.List;

public interface DailyIssueDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_issue
     *
     * @ibatorgenerated Wed Aug 10 17:28:40 CST 2011
     */
    int countByExample(DailyIssueExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_issue
     *
     * @ibatorgenerated Wed Aug 10 17:28:40 CST 2011
     */
    int deleteByExample(DailyIssueExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_issue
     *
     * @ibatorgenerated Wed Aug 10 17:28:40 CST 2011
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_issue
     *
     * @ibatorgenerated Wed Aug 10 17:28:40 CST 2011
     */
    void insert(DailyIssue record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_issue
     *
     * @ibatorgenerated Wed Aug 10 17:28:40 CST 2011
     */
    void insertSelective(DailyIssue record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_issue
     *
     * @ibatorgenerated Wed Aug 10 17:28:40 CST 2011
     */
    List selectByExample(DailyIssueExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_issue
     *
     * @ibatorgenerated Wed Aug 10 17:28:40 CST 2011
     */
    DailyIssue selectByPrimaryKey(Integer id);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_issue
     *
     * @ibatorgenerated Wed Aug 10 17:28:40 CST 2011
     */
    int updateByExampleSelective(DailyIssue record, DailyIssueExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_issue
     *
     * @ibatorgenerated Wed Aug 10 17:28:40 CST 2011
     */
    int updateByExample(DailyIssue record, DailyIssueExample example);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_issue
     *
     * @ibatorgenerated Wed Aug 10 17:28:40 CST 2011
     */
    int updateByPrimaryKeySelective(DailyIssue record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_issue
     *
     * @ibatorgenerated Wed Aug 10 17:28:40 CST 2011
     */
    int updateByPrimaryKey(DailyIssue record);
}