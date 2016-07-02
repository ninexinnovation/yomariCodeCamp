<?php
class Forum_model extends CI_Model{
	public function __construct(){
		// $this->load->database();
	}
	public function getAllForums(){
		$query=$this->db->order_by("forum_id","desc")->get("forum");
		return $query;
	}
	public function getForumById($id){
		$query=$this->db->where("forum_id",$id)->get("forum");
		return $query;
	}
	public function getComments($forum_id){
		$query=$this->db->get_where("forum_comments",["forum_id"=>$forum_id]);
		return $query;
	}
	public function addNewForum($topic,$username){
		$data["forum_title"]=$topic;
		$data["username"]=$username;
		$data["time"]=time();
		$this->db->insert("forum",$data);
		return $this->db->insert_id();		
	}
	public function addComment($forum_id,$comment,$username){
		$data["forum_id"]=$forum_id;
		$data["f_comment"]=$comment;
		$data["username"]=$username;
		$data["time"]=time();
		$this->db->insert("forum_comments",$data);
		return $this->db->insert_id();
	}

}
?>
























