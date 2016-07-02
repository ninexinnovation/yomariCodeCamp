<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Agrimetric</title>
<link href="<?php echo base_url(); ?>assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="<?php echo base_url(); ?>assets/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<link href="<?php echo base_url(); ?>assets/css/style.css" rel="stylesheet">

</head>
  <body>
    <div class="container-fluid">
    <?php
    if(isset($forums)){
      if($forums->num_rows()!=0){
        foreach ($forums->result() as $forum) {
            // var_dump($forum);
      ?>
        <div class="row forum-section" data-id="<?php echo $forum->forum_id ?>">
          <div class="col-xs-12">
            <div class="forum-head"><?php echo $forum->forum_title?></div>
            <div class="row forum-details">
              <div class="col-xs-4 col-xs-offset-8"><?php echo ucfirst($forum->username);?> <i><?php echo date("Y-m-d",$forum->time)?></i></div>
            </div>
          </div>
        </div>
      <?php
        }
      }
    }else if(isset($forum) && isset($comments)){
      if($forum->num_rows()==0){
        echo "no data";
      }else{
        $data=$forum->result()[0];
        // var_dump($data);
    ?>
      <div class="row forum-section">
        <div class="col-xs-12">
          <div class="forum-head"><?php echo $data->forum_title ?></div>
          <div class="row forum-details">
            <div class="col-xs-4 col-xs-offset-8"><?php echo ucfirst($data->username) ?> <i><?php echo date("Y-m-d",$data->time)?></i></div>
          </div>
          <div class="row forum-comments">
            <div class="col-xs-12">
    <?php
          if($comments->num_rows()!=0){
            foreach ($comments->result() as $comment) {
    ?>
              <div class="row comment">
                <div class="row">
                  <div class="col-xs-12 comment-username"><?php echo ucfirst($comment->username) ?></div>
                </div>
                <div class="row">
                  <div class="col-xs-12 comment-text"><?php echo $comment->f_comment ?></div>
                </div>
                <div class="row">
                  <div class="col-xs-3 col-xs-offset-9 comment-date"><?php echo date("Y-m-d",$comment->time)?></div>
                </div>
              </div>
    <?php
            }
          }else{
            echo "no comments";
          }
    ?>
            </div>
          </div>
        </div>
      </div>
    <?php
      }
    }
    ?>
    </div>
    <script src="<?php echo base_url(); ?>assets/js/jquery.min.js"></script>
    <script src="<?php echo base_url(); ?>assets/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript">
      $(document).ready(function(){
        $(".forum-section").on("click",function(){
          var id=$(this).attr("data-id");
          if(id!=undefined){
            window.location = '<?php echo base_url() ?>index.php/forum/index/comments?id=' + id;
          }
        });
      });
    </script>
  </body>
</html>