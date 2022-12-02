import http from "../http-common";

class UploadFileService {

    get(id) {
        return http.get(`/files/${id}`);

    }

    getByName(nome){
        
    }

    upload(file) {          

        let formData = new FormData();
    
        formData.append("file", file);
    
        let response = http.post("/upload", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        }).then((data) => {
          return data.data;
          
        });
        
        
        return response;
    

      }

}

export default new UploadFileService();