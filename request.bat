@echo off
set LOGFILE=C:\Main\request_log.txt
echo [%DATE% %TIME%] Starting request.bat - sending status=1 ping >> "%LOGFILE%"
"C:\Main\curl" --data "id=5&status=1" https://c451f437.ngrok.io/update_ping.php >> "%LOGFILE%" 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo [%DATE% %TIME%] ERROR: curl request failed with exit code %ERRORLEVEL% >> "%LOGFILE%"
) else (
    echo [%DATE% %TIME%] curl request completed successfully >> "%LOGFILE%"
)
echo [%DATE% %TIME%] Finished request.bat >> "%LOGFILE%"
