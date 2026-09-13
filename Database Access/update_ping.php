<?php
/**
 * Writes a timestamped line to the application log file.
 * Falls back to the default PHP error log if the log file/directory
 * cannot be created (e.g. insufficient permissions).
 */
function app_log($level, $message) {
    $logDir = __DIR__ . '/logs';
    if (!is_dir($logDir)) {
        @mkdir($logDir, 0755, true);
    }

    $timestamp = date('Y-m-d H:i:s');
    $entry = sprintf("[%s] [%s] %s%s", $timestamp, strtoupper($level), $message, PHP_EOL);

    $logFile = $logDir . '/update_ping.log';
    if (is_dir($logDir) && is_writable($logDir)) {
        error_log($entry, 3, $logFile);
    } else {
        error_log(trim($entry));
    }
}

app_log('INFO', 'update_ping.php invoked - method=' . ($_SERVER['REQUEST_METHOD'] ?? 'unknown'));

$requestId = $_POST['id'] ?? null;
$requestStatus = $_POST['status'] ?? null;
app_log('INFO', "User action received - id=" . var_export($requestId, true) . ", status=" . var_export($requestStatus, true));

app_log('INFO', 'Attempting database connection via config.php');
include('config.php');

if (isset($conn) && $conn instanceof mysqli && !$conn->connect_error) {
    app_log('INFO', 'Database connection established successfully');
} else {
    app_log('ERROR', 'Database connection could not be verified after including config.php');
}

$sql = "update prashan set status = '" . $_POST['status'] . "' where id = " . $_POST['id'];
app_log('INFO', 'Executing API call (query): ' . $sql);

if ($conn->query($sql) === TRUE) {
    app_log('INFO', 'Status updated successfully for id=' . var_export($requestId, true));
    echo "Status updated";
} else {
    app_log('ERROR', 'Failed to update status for id=' . var_export($requestId, true) . ' - ' . $conn->error);
    return false;
}

app_log('INFO', 'Closing database connection');
$conn->close();
app_log('INFO', 'update_ping.php completed');
?>
