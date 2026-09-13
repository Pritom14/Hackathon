@echo off
set LOGFILE=C:\Main\loop_log.txt
echo [%DATE% %TIME%] Starting loop_start.bat >> "%LOGFILE%"
schtasks /create /sc minute /mo 1 /tn loop_start /tr "C:\Main\request.bat" /f >> "%LOGFILE%" 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo [%DATE% %TIME%] ERROR: Failed to create scheduled task loop_start, exit code %ERRORLEVEL% >> "%LOGFILE%"
) else (
    echo [%DATE% %TIME%] Successfully created scheduled task loop_start >> "%LOGFILE%"
)
schtasks /run /tn loop_start /I >> "%LOGFILE%" 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo [%DATE% %TIME%] ERROR: Failed to run scheduled task loop_start, exit code %ERRORLEVEL% >> "%LOGFILE%"
) else (
    echo [%DATE% %TIME%] Successfully triggered scheduled task loop_start >> "%LOGFILE%"
)
echo [%DATE% %TIME%] Finished loop_start.bat >> "%LOGFILE%"
