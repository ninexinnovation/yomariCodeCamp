<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Forum extends CI_Controller {
	public function __construct(){
		parent::__construct();
		$this->load->model("Forum_model");
	}
	public function index($function=null)
	{
		if($function==null){
			$data["forums"]=$this->Forum_model->getAllForums();
			$this->load->view('forum',$data);
		}else if($function=="comments"){
			$forum_id=$this->input->get("id");
			$data['forum']=$this->Forum_model->getForumById($forum_id);
			$data['comments']=$this->Forum_model->getComments($forum_id);
			$this->load->view('forum',$data);
		}
		// print_r($this->Forum_model->getComments(1)->result());
	}
	public function postForum($type=null){
		$title=$this->input->post("title");
		$username=$this->input->post("username");

		$this->form_validation->set_rules('title', 'Title', 'required');
		$this->form_validation->set_rules('username', 'Username', 'required');
		// var_dump($this->form_validation->run());
		if($this->form_validation->run()===FALSE){
			if($type=="ajax"){
				echo json_encode(["data"=>[
					"success"=>false,
					"message"=>$this->form_validation->error_array()
				]]);
			}else{

			}
		}else{
			$return=$this->Forum_model->addNewForum($title,$username);
			if($type=="ajax"){
				echo json_encode(["data"=>[
					"success"=>false,
					"message"=>$return
				]]);
			}else{

			}
		}
	}
	public function postComment($type=null){

		$id=$this->input->post("id");
		$comment=$this->input->post("comment");
		$username=$this->input->post("username");

		$this->form_validation->set_rules('id', "ID", 'required');
		$this->form_validation->set_rules('comment', 'Comment', 'required');
		$this->form_validation->set_rules('username', 'Username', 'required');
		// var_dump($this->form_validation->run());
		if($this->form_validation->run()===FALSE){
			if($type=="ajax"){
				echo json_encode(["data"=>[
					"success"=>false,
					"message"=>$this->form_validation->error_array()
				]]);
			}else{

			}
		}else{
			$return=$this->Forum_model->addComment($id,$comment,$username);
			if($type=="ajax"){
				echo json_encode(["data"=>[
					"success"=>false,
					"message"=>$return
				]]);
			}else{

			}
		}
	}
}
