<?php
   include('config.php');
   include('logger.php');
   session_start();

   app_log('INFO', 'Session check started', array('session_id' => session_id()));

   $user_check = $_SESSION['login_user'];

   $ses_sql = mysqli_query($conn,"SELECT name FROM prashan WHERE name = '".$user_check."'");

   $row = mysqli_fetch_array($ses_sql,MYSQLI_ASSOC);

   $login_session = $row['name'];
   app_log('INFO', 'User validated against database', array('login_user' => $user_check, 'found' => isset($row['name'])));
   echo $row['name'];
   if(!isset($_SESSION['login_user'])){
      app_log('WARNING', 'No active session found, redirecting to login', array('session_id' => session_id()));
      header("location:index.php");
      echo 'dff';
   }
?>