<?php
   // Centralized logging helper for the authentication/session layer.
   // Writes to PHP's configured error log via error_log().
   function app_log($level, $message, array $context = array()) {
      $timestamp = date('Y-m-d H:i:s');
      $contextStr = '';
      if (!empty($context)) {
         $contextStr = ' ' . json_encode($context);
      }
      error_log("[$timestamp] [$level] $message$contextStr");
   }
?>
