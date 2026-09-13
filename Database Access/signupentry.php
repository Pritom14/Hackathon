<?php
   include("config.php");
   include("logger.php");

   app_log('INFO', 'Signup attempt received', array('name' => $_POST['name'], 'department' => $_POST['department']));

   $sql = "INSERT INTO prashan (name,department,status)
   VALUES ('".$_POST['name']."','".$_POST['department']."',0)";

   if ($conn->query($sql) === TRUE) {
       app_log('INFO', 'Signup successful, new record created', array('name' => $_POST['name']));
       echo "New record created successfully";
   } else {
       app_log('ERROR', 'Signup failed', array('name' => $_POST['name'], 'error' => $conn->error));
       echo "Error: " . $sql . "<br>" . $conn->error;
   }

   $conn->close();
?>