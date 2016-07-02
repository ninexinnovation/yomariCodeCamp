<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class MainController extends CI_Controller {
	public function __construct(){
		parent::__construct();
		$this->load->model("Data_model");
	}
	public function index()
	{
		$this->load->view('main');
	}
	public function api($function){
		switch ($function) {
			case 'getSystemStatus':
					$id=$this->input->get("id");
					$dataType=$this->input->get("dataType");

					if($id!=null){
						$returns=$this->Data_model->getSystemStatus($id);
						if($dataType=="json"){
							echo json_encode(["data"=>
								[
									"success"=>"true",
									"messageType"=>"systemStatus",
									"message"=>$returns[0]
								]
							]);
						}else{
							echo $returns[0]->system_status;
						}
					}
				break;
			case 'getMotorStatus':
					$id=$this->input->get("id");
					$dataType=$this->input->get("dataType");

					if($id!=null){
						$returns=$this->Data_model->getMotorStatus($id);
						if($dataType=="json"){
							echo json_encode(["data"=>
								[
									"success"=>"true",
									"messageType"=>"motorStatus",
									"message"=>$returns[0]
								]
							]);
						}else{
							echo "<".$returns[0]->motor_status;
						}
					}
				break;
			case 'setSystemStatus':
					$id=$this->input->get("id");
					$status=$this->input->get("status");
					$dataType=$this->input->get("dataType");

					if($id!=null && $status!=null){
						$returns=$this->Data_model->setSystemStatus($id,$status);
						if($dataType=="json"){
							echo json_encode(["data"=>
								[
									"success"=>"true",
									"messageType"=>"systemStatus",
									"message"=>$returns
								]
							]);
						}else{
							echo $returns["system_status"];
						}
					}
				break;

			case 'setMotorStatus':
					$id=$this->input->get("id");
					$status=$this->input->get("status");
					$dataType=$this->input->get("dataType");

					if($id!=null && $status!=null){
						$returns=$this->Data_model->setMotorStatus($id,$status);
						if($dataType=="json"){
							echo json_encode(["data"=>
								[
									"success"=>"true",
									"messageType"=>"motorStatus",
									"message"=>$returns
								]
							]);
						}else{
							echo $returns["motor_status"];
						}
					}
					// $this->api("getSystemStatus");
				break;

			case 'setMoisture':
					$id=$this->input->get("id");
					$data=$this->input->get("data");

					if($id!=null && $data!=null){
						var_dump($this->Data_model->addMoisture($id,$data));
					}
				break;

			case 'setHumidity':
					$id=$this->input->get("id");
					$data=$this->input->get("data");

					if($id!=null && $data!=null){
						var_dump($this->Data_model->addHumidity($id,$data));
					}
				break;

			case 'setTemperature':
					$id=$this->input->get("id");
					$data=$this->input->get("data");

					if($id!=null && $data!=null){
						var_dump($this->Data_model->addTemp($id,$data));
					}
				break;

			case 'getMoisture':
					$id=$this->input->get("id");
					var_dump($this->Data_model->getCurrentMoistureData($id));
				break;
			case 'getHumidity':
					$id=$this->input->get("id");
					var_dump($this->Data_model->getCurrentHumidityData($id));
				break;
			case 'getTemperature':
					$id=$this->input->get("id");
					var_dump($this->Data_model->getCurrentTempData($id));
				break;
			case 'getAllData':
					$id=$this->input->get("id");
					$moisture=$this->Data_model->getCurrentMoistureData($id);
					$humidity=$this->Data_model->getCurrentHumidityData($id);
					$temperature=$this->Data_model->getCurrentTempData($id);
					$motor=$this->Data_model->getMotorStatus($id);
					$system=$this->Data_model->getSystemStatus($id);
					if($moisture!=false)
						$data["moisture"]=$moisture->data;
					else
						$data["moisture"]="0";

					if($humidity!=false)
						$data["humidity"]=$humidity->data;
					else
						$data["humidity"]="0";

					if($temperature!=false)
						$data["temperature"]=$temperature->data;
					else
						$data["temperature"]="0";
				
					if($motor!=false)
						$data["motor"]=$motor[0]->motor_status;
					else
						$data["motor"]=0;
					
					if($system!=false)
						$data["system"]=$system[0]->system_status;
					else
						$data["system"]=0;
					
				
					echo json_encode(["data"=>
						[
							"success"=>"true",
							"messageType"=>"allData",
							"message"=>$data
						]
					]);
				break;
				
	    }
	}
}
