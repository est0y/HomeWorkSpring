package ru.est0y.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@RequiredArgsConstructor
@ShellComponent
public class BookMigrationCommands {
    private final JobLauncher jobLauncher;

    private final Job libraryMigrationJob;

    @ShellMethod(value = "startMigration", key = "sm")
    public void startMigration() throws Exception {
        JobExecution execution = jobLauncher.run(libraryMigrationJob, new JobParametersBuilder()
                .toJobParameters());
        System.out.println(execution);
    }
}
