@echo off
set LOGFILE=C:\Main\task_setup_log.txt
echo [%DATE% %TIME%] Starting task_startup_setup.bat >> "%LOGFILE%"
schtasks.exe /Create /XML C:\Users\Pratham\Desktop\Main\startup_task.xml /tn startup_task /F >> "%LOGFILE%" 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo [%DATE% %TIME%] ERROR: Failed to create startup_task, exit code %ERRORLEVEL% >> "%LOGFILE%"
) else (
    echo [%DATE% %TIME%] Successfully created startup_task >> "%LOGFILE%"
)
echo [%DATE% %TIME%] Finished task_startup_setup.bat >> "%LOGFILE%"
