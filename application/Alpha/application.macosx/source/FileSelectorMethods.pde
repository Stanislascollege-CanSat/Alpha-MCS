// FileSelectorMethods.pde
// Processing 3.4
// Rens Dur (Project Bèta)


//-----------------------------------------------------------------------------------------------------------------//
// AppController -> (void)SetupView_ask_folder_MissionData -> [callback](void)SetupView_selected_folder_MissionData -> AppController_Interface

/*init*/public void SetupView_ask_folder_MissionData(){
  selectFolder("Select to store the mission data.", "SetupView_selected_folder_MissionData");
}

/*callback*/public void SetupView_selected_folder_MissionData(File selected){
  appController.SetupView_selected_folder_MissionData(selected);
}
