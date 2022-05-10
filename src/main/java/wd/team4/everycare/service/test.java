//package wd.team4.everycare.service;
//
//@Override
//public ResponseEntity<MyResponse> getSchedule(Long id) {
//        Optional<CareTargetSchedule> careTargetSchedule = careTargetScheduleRepository.findById(id);
//        케어타겟 스케줄 한개
//        CareTargetSchedule careTargetScheduleEntity = careTargetSchedule.orElse(null);
//        있는지 없는지

//        CareTargetScheduleDTO careTargetScheduleDTO = careTargetScheduleEntity.toDTO();
//        DTO화

//        List<ActivityInformation> activityInformations = activityInformationRepository.findAllByCareTargetScheduleId(careTargetScheduleEntity.getId());
//        활동정보 리스트

//        List<ActivityInformationViewDTO> activityInformationViewDTOs = new ArrayList<>();
//        활동정보 리스트 담을 ArrayList

//        for (ActivityInformation activityInformation : activityInformations) {
//        ActivityInformationViewDTO activityInformationViewDTO = ActivityInformationViewDTO.builder()
//        .id(activityInformation.getId())
//        .startTime(activityInformation.getStartTime())
//        .endTime(activityInformation.getEndTime())
//        .requirement(activityInformation.getRequirement())
//        .activityClassificationDTO(activityInformation.getActivityClassification().toDTO())
//        .build();
//
//        activityInformationViewDTOs.add(activityInformationViewDTO);
//        }
//        for:each로 각각 ArrayList에 담음

//        ActivityInformationListViewDTO activityInformationListViewDTO = ActivityInformationListViewDTO.builder()
//        .careTargetScheduleDTO(careTargetScheduleDTO)
//        .activityInformationViewDTOs(activityInformationViewDTOs)
//        .build();
//
//        MyResponse<ActivityInformationListViewDTO> body = MyResponse.<ActivityInformationListViewDTO>builder()
//        .header(StatusEnum.OK)
//        .message("성공")
//        .body(activityInformationListViewDTO)
//        .build();
//
//        HttpHeaders headers = new HttpHeaders();
//        return new ResponseEntity<MyResponse>(body, headers, HttpStatus.OK);
//        }