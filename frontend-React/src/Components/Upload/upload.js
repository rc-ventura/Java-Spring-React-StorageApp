import React, { Component } from "react";
import axios from "axios";



class Upload extends Component {
  constructor(props) {
    super(props);
    this.state = {
      id: "",
      arquivo: undefined,
      idArquivo: ''
    };
  }

  selecionaArquivo = (event) => {
    this.arquivo = event.target.files[0];
  };

  enviarArquivo = (event) => {
    event.preventDefault();

    console.log(this.arquivo);

    const url = "http://localhost:8080/upload";
    const formData = new FormData();

    formData.append("file", this.arquivo);
    formData.append("fileName", this.arquivo.name);

    const config = {
      headers: {},
    };

    axios.post(url, formData, config).then((response) => {
      console.log(response);
      this.idArquivo = response.data.id;
      document.getElementById("arquivo").value = this.idArquivo;
    });
    
    alert("Arquivo importado com sucesso!");
    console.log(this.arquivo);



    return this.arquivo;
  };

  


  render() {
    return (
      <div className="  mb-4  " >

        <label htmlFor="fname" className="label render">
          <strong>Logo</strong>
        </label>
        <br></br>

        <input hidden id="arquivo" value={""}></input>

        <br></br>
        <input type="file" accept="image/*" onChange={this.selecionaArquivo} />

        <span className="mb-5">
        <button type="submit" className="btn btn-primary"  onClick={this.enviarArquivo}>
          Upload
        </button>
        </span>
        <br></br>

        <br></br>
      </div>
    );
  }
}

export default Upload;
