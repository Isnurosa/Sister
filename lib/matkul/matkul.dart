// ignore_for_file: prefer_const_literals_to_create_immutables, prefer_const_constructors

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'package:sisterservicegui/matkul/insertMatkul.dart';
import 'package:sisterservicegui/matkul/updateMatkul.dart';

class DataMatkul extends StatefulWidget {
  const DataMatkul({super.key});

  @override
  State<DataMatkul> createState() => _DataMatkulState();
}

class _DataMatkulState extends State<DataMatkul> {
  List listMatkul = [];

  @override
  void initState() {
    allMatkul();
    super.initState();
  }

  Future<void> allMatkul() async {
    String urlMatkul = "http://192.168.56.1:9002/api/v1/matkul";
    try {
      var response = await http.get(Uri.parse(urlMatkul));
      listMatkul = jsonDecode(response.body);
      setState(() {
        listMatkul = jsonDecode(response.body);
      });
    } catch (exc) {
      print(exc);
    }
  }

  Future<void> deleteMatkul(int id) async {
    String urlMatkul = "http://192.168.56.1:9002/api/v1/matkul/${id}";
    try {
      await http.delete(Uri.parse(urlMatkul));
      setState(() {
        allMatkul();
      });
    } catch (exc) {
      print(exc);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Data Matakuliah"),
        backgroundColor: Color.fromARGB(255, 224, 76, 150),
        actions: [
          IconButton(
              onPressed: () {
                Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (context) => InsertMatkul()))
                    .then((value) {
                  allMatkul();
                });
              },
              icon: Icon(
                Icons.add,
                size: 30,
              ))
        ],
      ),
      body: listMatkul.isEmpty
          ? Center(
              child: Text(
                'Tidak ada data',
                style: TextStyle(fontSize: 20),
              ),
            )
          : ListView.builder(
              itemCount: listMatkul.length,
              itemBuilder: (context, index) {
                return Card(
                  margin: EdgeInsets.all(5),
                  child: ListTile(
                    leading: Icon(
                      Icons.book_outlined,
                      color: Colors.green.shade500,
                      size: 24,
                    ),
                    title: Text(
                      listMatkul[index]["kode"]?.toString() ?? "",
                      style: TextStyle(
                          color: Colors.green,
                          fontSize: 17,
                          fontWeight: FontWeight.bold),
                    ),
                    subtitle: Text(
                      "Nama : ${listMatkul[index]["nama"]?.toString() ?? ""}\nSKS : ${listMatkul[index]["sks"]?.toString() ?? ""}",
                      style: TextStyle(
                          color: Colors.black,
                          fontSize: 12,
                          fontWeight: FontWeight.normal),
                    ),
                    trailing: Row(
                      mainAxisSize: MainAxisSize.min,
                      children: [
                        IconButton(
                            tooltip: "Hapus Data",
                            onPressed: () {
                              deleteMatkul(listMatkul[index]["id"]);
                            },
                            icon: Icon(
                              Icons.delete,
                              color: Colors.red.shade300,
                              size: 24,
                            )),
                        IconButton(
                            tooltip: "Edit Data",
                            onPressed: () {
                              Navigator.push(
                                      context,
                                      MaterialPageRoute(
                                          builder: (context) =>
                                              UpdateMatkul(
                                                  listMatkul[index]["id"],
                                                  listMatkul[index]["kode"],
                                                  listMatkul[index]["nama"],
                                                  listMatkul[index]["sks"]
                                                      .toString())))
                                  .then((value) => allMatkul());
                            },
                            icon: Icon(
                              Icons.edit,
                              color: Colors.yellow.shade800,
                              size: 24,
                            )),
                      ],
                    ),
                  ),
                );
              }),
    );
  }
}
