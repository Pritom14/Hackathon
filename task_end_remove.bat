@echo off
set LOGFILE=C:\Main\task_log.txt
echo [%DATE% %TIME%] Starting task_end_remove.bat - deleting scheduled task startup_task >> "%LOGFILE%"
schtasks /delete /TN startup_task /F >> "%LOGFILE%" 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo [%DATE% %TIME%] ERROR: Failed to delete task startup_task, exit code %ERRORLEVEL% >> "%LOGFILE%"
) else (
    echo [%DATE% %TIME%] Successfully deleted task startup_task >> "%LOGFILE%"
)
echo [%DATE% %TIME%] Finished task_end_remove.bat >> "%LOGFILE%"
