package com.pblgllgs.job;
/*
 *
 * @author pblgl
 * Created on 21-10-2024
 *
 */

import java.util.List;

public interface JobService {

    List<Job> findAllJobs();
    void createJob(Job job);
    Job findJobById(Long id);
    boolean deleteJobById(Long id);
    boolean updateJob(Long id, Job job);
}
