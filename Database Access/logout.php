
<?php
   include("logger.php");
   session_start();

   $session_id = session_id();
   app_log('INFO', 'Session destruction requested', array('session_id' => $session_id));

   if(session_destroy()) {
      app_log('INFO', 'Session destroyed successfully', array('session_id' => $session_id));
      header("Location: login.php");
   } else {
      app_log('ERROR', 'Session destruction failed', array('session_id' => $session_id));
   }
?>