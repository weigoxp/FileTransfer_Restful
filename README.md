# FileTransfer_Restful

* Use H2 as metainfo storage  
* file storage path locates in application.properties

  API: 

  **1. Upload file/metadata**  

  post localhost:8080/file \
  uses form data to upload file/metadata(json format)

  example:  
  key--------------value 

  file------------sample.jpg  
  meta------------{"name":"sample.jpg","size":11111,"created": "2020-09-11"}

  reponse returns file id and http code

  **2. Download metadata by fileID**  

  get localhost:8080/meta/{id}


  **3. Download file stream by fileID**  

  get localhost:8080/file/{id}

  **4. Get file ids which has size greater than the parameter**  

  get localhost:8080/file?sizeGreater=???
