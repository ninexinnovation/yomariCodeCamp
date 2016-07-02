<?php
class Data_model extends CI_Model{
	public function __construct(){
		// $this->load->database();
	}
	public function getSystemStatus($id){
		$query=$this->db->where("system_id",$id)->get("system_status");
		if($query->num_rows()==1){
			return $query->result();
		}else{
			$data["system_id"]=$id;
			$data["system_status"]=1;
			$data["time"]=time();
			$this->db->insert("system_status",$data);

			$query=$this->db->where("system_id",$id)->get("system_status");
			return $query->result();
		}
	}
	public function getMotorStatus($id){
		$query=$this->db->where("system_id",$id)->get("motor_status");
		if($query->num_rows()==1){
			return $query->result();
		}else{
			$data["system_id"]=$id;
			$data["motor_status"]=1;
			$data["time"]=time();
			$this->db->insert("motor_status",$data);

			$query=$this->db->where("system_id",$id)->get("motor_status");
			return $query->result();
		}
	}
	public function getCurrentMoistureData($id){
		$query=$this->db->where("system_id",$id)->order_by("moisture_id","desc")->get("moisture");
		// var_dump($query->result());
		if($query->num_rows()!=0)
			return $query->result()[0];
		else return false;
	}
	public function getCurrentHumidityData($id){
		$query=$this->db->where("system_id",$id)->order_by("humidity_id","desc")->get("humidity");
		if($query->num_rows()!=0)
			return $query->result()[0];
		else return false;
	}
	public function getCurrentTempData($id){
		$query=$this->db->where("system_id",$id)->order_by("temp_id","desc")->get("temperature");
		if($query->num_rows()!=0)
			return $query->result()[0];
		else return false;
	}



	public function setSystemStatus($id,$status){
		$data["system_id"]=$id;
		$data["system_status"]=$status;
		$data["time"]=time();
		$this->db->where("system_id",$id);
		if($this->db->get("system_status")->num_rows()==1)
			$this->db->update("system_status",$data);
		else
			$this->db->insert("system_status",$data);
		return $data;
	}
	public function setMotorStatus($id,$status){
		$data["system_id"]=$id;
		$data["motor_status"]=$status;
		$data["time"]=time();
		$this->db->where("system_id",$id);
		if($this->db->get("motor_status")->num_rows()==1)
			$this->db->update("motor_status",$data);
		else
			$this->db->insert("motor_status",$data);
		return $data;
	}
	public function addMoisture($id,$dat){
		$data["system_id"]=$id;
		$data["data"]=$dat;
		$data["time"]=time();
		$this->db->insert("moisture",$data);
		return $data;
	}
	public function addHumidity($id,$dat){
		$data["system_id"]=$id;
		$data["data"]=$dat;
		$data["time"]=time();
		$this->db->insert("humidity",$data);
		return $data;
	}
	public function addTemp($id,$dat){
		$data["system_id"]=$id;
		$data["data"]=$dat;
		$data["time"]=time();
		$this->db->insert("temperature",$data);
		return $data;
	}

}
?>
























