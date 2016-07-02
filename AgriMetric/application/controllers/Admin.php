<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Forum extends CI_Controller {
	public function __construct(){
		parent::__construct();
		$this->load->model("Forum_model");
	}
	public function index()
	{
		$this->load->view('main');
	}
}
