package com.pblgllgs.job.impl;
/*
 *
 * @author pblgl
 * Created on 21-10-2024
 *
 */

import com.pblgllgs.job.Job;
import com.pblgllgs.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class JobServiceImpl implements JobService {

    private final List<Job> jobs = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<Job> findAllJobs() {
        return jobs;
    }

    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        jobs.add(job);
    }

    @Override
    public Job findJobById(Long id) {
        return jobs.stream().filter(job -> Objects.equals(job.getId(), id)).findFirst().orElse( null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        Job job = findJobById(id);
        if (job != null) {
            jobs.remove(job);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateJob(Long id, Job job) {
        Job jobToUpdate = findJobById(id);
        if (jobToUpdate != null) {
            jobToUpdate.setDescription(job.getDescription());
            jobToUpdate.setLocation(job.getLocation());
            jobToUpdate.setTitle(job.getTitle());
            jobToUpdate.setMaxSalary(job.getMaxSalary());
            jobToUpdate.setMinSalary(job.getMinSalary());
            return true;
        }
        return false;
    }
}
