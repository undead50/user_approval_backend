package com.ebl.userapproval.controller;


import com.ebl.userapproval.dto.SignatureRequestDto;
import com.ebl.userapproval.model.Employee;
import com.ebl.userapproval.repository.EmployeeRepository;
import com.ebl.userapproval.service.ApimsApiService;
import com.ebl.userapproval.service.CallCbs;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@RestController
    @RequestMapping("/api")
    public class LoginController {


        @Autowired
        private EmployeeRepository employeeRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Autowired
        private ApimsApiService apimsApiService;



        @RequestMapping("/post-login")
        public Employee getUserDetailsAfterLogin(Authentication authentication) {
            System.out.println(authentication.getName());
            List<Employee> employees = employeeRepository.findBydomainName(authentication.getName());
            if (employees.size() > 0) {
                return employees.get(0);
            } else {
                return null;
            }

        }

//        @PostMapping("/register")
//        public ResponseEntity<String> registerUser(@Validated @RequestBody Employee employee) {
//            Employee savedEmployee = null;
//            ResponseEntity response = null;
//            LocalDateTime now = LocalDateTime.now();
//
//            // Format timestamp to a desired format
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
//            String timestamp = now.format(formatter);
//            var employeeData = employeeRepository.findByCbsUsername(employee.getCbsUsername());
//            if(employeeData.size()>0){
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Employee Already Registered");
//            }
//            SignatureRequestDto signatureRequestDto = new SignatureRequestDto();
//            signatureRequestDto.setFunctionName("UserInfoCBS");
//            Map<String, Object> request = new HashMap<>();
//            request.put("TransactionId",timestamp);
//            request.put("emp_id", employee.getCbsUsername());
//            signatureRequestDto.setRequest(request);
//            var apiResponse = apimsApiService.mainApiService(signatureRequestDto).getBody();
//            Gson gson = new Gson();
//            String serializedString = gson.toJson(apiResponse);
//            JsonObject jsonObject = gson.fromJson(serializedString, JsonObject.class);
//            if (Objects.equals(jsonObject.get("Code").getAsString(), "0")){
//                JsonObject data = jsonObject.getAsJsonObject("Data");
//                JsonArray queryResult = data.getAsJsonArray("QueryResult");
//                JsonObject uprResult = queryResult.get(0).getAsJsonObject();
//                String cbsUserId = String.valueOf(uprResult.get("USER_ID")).replaceAll("^\"|\"$", "");
//                System.out.println(cbsUserId);
//                System.out.println(employee.getCbsUsername());
//                if (cbsUserId.equals(employee.getCbsUsername().toUpperCase())){
//                    try {
//                        String hashPwd = passwordEncoder.encode(employee.getPwd());
//                        employee.setPwd(hashPwd);
//                        employee.setEmail(String.valueOf(uprResult.get("EMP_EMAIL_ID")).replaceAll("^\"|\"$", ""));
//                        employee.setEmployeeDesignation(String.valueOf(uprResult.get("DESIGNATION")).replaceAll("^\"|\"$", ""));
//                        employee.setName(String.valueOf(uprResult.get("EMP_NAME")).replaceAll("^\"|\"$", ""));
//                        employee.setBranch(String.valueOf(uprResult.get("SOL_ID")).replaceAll("^\"|\"$", ""));
//                        employee.setRole("ROLE_USER");
//                        employee.setIsCbsUser("Y");
//                        employee.setCbsUsername(employee.getCbsUsername());
//                        savedEmployee = employeeRepository.save(employee);
//                        if (savedEmployee.getId() > 0) {
//                            response = ResponseEntity
//                                    .status(HttpStatus.CREATED)
//                                    .body("Given Employee details are successfully registered");
//                        }
//                    } catch (Exception ex) {
//                        response = ResponseEntity
//                                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                                .body("An exception occured due to " + ex.getMessage());
//                    }
//                }
//
//            } else {
//                response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Employee Dosen't Exsist in CBS");
//            }
//
//
//            return response;
//        }
    }


