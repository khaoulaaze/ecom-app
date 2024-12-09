import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import * as http from "http";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  products:any;
  constructor(private http:HttpClient) { }

  ngOnInit(): void {
    this.http.get(`http://localhost:8888/inventory-service/products`).subscribe({
      next: (data)=>{
        this.products=data;
      },
      error : (err) =>{}
    });
  }

}
