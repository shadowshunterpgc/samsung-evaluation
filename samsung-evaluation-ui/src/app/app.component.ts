import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {
  title = 'samsung-evaluation-ui';
  numeroPesquisa: string = '';
  descricaoMoeda: string = '';
  codigoMoeda: string = '';
  dataDocumento: string = '';
  dataCotacao: string = '';
  resultado: string = '';
  moedas: string[] = []; // Array para armazenar as moedas

  displayedColumns: string[] = ['documentNumber', 'documentDate', 'currencyCode', 'currencyDesc', 'documentValue', 'valueUSD', 'valuePEN', 'valueBRL'];
  dataSourceConsultaTodos: MatTableDataSource<any> = new MatTableDataSource<any>([]);
  dataSourceCombo: MatTableDataSource<any> = new MatTableDataSource<any>([]);

  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.preencheCombo();
  }

  pesquisar(documentNumber: string, codigoMoeda: string, dataDocumento: string, dataCotacao: string, descricaoMoeda: string) {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    if (descricaoMoeda !== null) {

      const requestBody = {
        documentNumber: this.numeroPesquisa,
        currencyDesc: this.descricaoMoeda,
        currencyCode: this.codigoMoeda,
        documentDate: this.dataDocumento,
        dataHoraCotacao: this.dataCotacao,
      };

      const url = "http://localhost:8080/api/cotacao/consulta";

      this.http.post(url, requestBody, { headers: headers }).subscribe(
        (data: any) => {
          // Lógica para lidar com os dados da primeira solicitação
          this.dataSourceConsultaTodos.data = data;
          this.dataDocumento = data[0].documentDate;
          this.codigoMoeda = data[0].currencyCode;
          console.log(data);
      
          // Segunda solicitação usando os dados da primeira solicitação
          const requestBody = {
            documentNumber: this.numeroPesquisa,
            currencyDesc: this.descricaoMoeda,
            currencyCode: this.codigoMoeda,
            documentDate: this.dataDocumento,
            dataHoraCotacao: this.dataCotacao,
          };
          
          const urlCotacao = "http://localhost:8080/api/cotacao/consulta";
          this.http.post(urlCotacao, requestBody, { headers: headers }).subscribe(
            (data: any) => {
              this.dataSourceConsultaTodos.data = data;
              console.log(data);
            },
            (error) => {
              console.error("Erro na segunda solicitação:", error);
            }
          );
        },
        (error) => {
          console.error("Erro na primeira solicitação:", error);
        }
      );  
      

    } else if (this.numeroPesquisa !== null) {
        const requestBodyNumeroPesquisa = {
          documentNumber: this.numeroPesquisa,
          currencyDesc: this.descricaoMoeda,
          currencyCode: this.codigoMoeda,
          documentDate: this.dataDocumento,
          dataHoraCotacao: this.dataCotacao,
        };

        const urlDocumento = "http://localhost:8080/api/documento/consulta";

        this.http.post(urlDocumento, requestBodyNumeroPesquisa, { headers: headers }).subscribe(
          (data: any) => {
            this.dataSourceConsultaTodos.data = data;
            this.dataDocumento = data.documentDate;
            this.codigoMoeda = data.currencyCode;
            console.log(data);

            const requestBody = {
              documentNumber: this.numeroPesquisa,
              currencyDesc: this.descricaoMoeda,
              currencyCode: this.codigoMoeda,
              documentDate: this.dataDocumento,
              dataHoraCotacao: this.dataCotacao,
            };

            const urlCotacao = "http://localhost:8080/api/cotacao/consulta";
            this.http.post(urlCotacao, requestBody, { headers: headers }).subscribe(
              (data: any) => {
                this.dataSourceConsultaTodos.data = data;
                console.log(data);
              },
              (error) => {
                console.error(error);
              }
            );
          },
          (error) => {
            console.error(error);
          }
      );
    }
  }

  preencheCombo() {
    this.http.get<any[]>('http://localhost:8080/api/moeda/consulta').subscribe(
      (data: any[]) => {
        this.moedas = data.map(item => item.currencyDesc);
        console.log(data);
      },
      (error) => {
        console.error(error);
      }
    );
  }

  limparFormulario() {
    this.numeroPesquisa = '';
    this.descricaoMoeda = '';
    this.dataDocumento = '';
    this.dataCotacao = '';
    this.resultado = '';
  }

  limparTabela() {
    this.dataSourceConsultaTodos.data = [];
  }
}
