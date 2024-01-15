// ignore_for_file: prefer_const_constructors, prefer_const_literals_to_create_immutables

import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

class InsertMahasiswa extends StatefulWidget {
  const InsertMahasiswa({super.key});

  @override
  State<InsertMahasiswa> createState() => _InsertMahasiswaState();
}

class _InsertMahasiswaState extends State<InsertMahasiswa> {
  final nama = TextEditingController();
  final email = TextEditingController();
  String namaMhs = "";
  String emailMhs = "";
  DateTime tglLahir = DateTime.now();

  Future<void> _pilihTgl(BuildContext context) async {
    final DateTime? kalender = await showDatePicker(
        context: context,
        initialDate: tglLahir,
        firstDate: DateTime(1950),
        lastDate: DateTime(2030));

    if (kalender != null && kalender != tglLahir) {
      setState(() {
        tglLahir = kalender;
      });
    }
  }

  Future<void> insertMahasiswa() async {
    String urlInsert = "http://192.168.56.1:9001/api/v1/mahasiswa";
    final Map<String, dynamic> data = {
      "nama": namaMhs,
      "email": emailMhs,
      "tglLahir": '${tglLahir.toLocal()}'.split(' ')[0]
    };

    try {
      var response = await http.post(Uri.parse(urlInsert),
          body: jsonEncode(data),
          headers: {'Content-Type': 'application/json'});

      if (response.statusCode == 200) {
        Navigator.pop(context, "berhasil");
      } else {
        print(response.statusCode);
      }
    } catch (e) {
      print(e);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Insert Data Mahasiswa"),
        backgroundColor: Color.fromARGB(255, 224, 76, 150),
      ),
      body: Center(
        child: Container(
          margin: EdgeInsets.fromLTRB(0, 100, 0, 0),
          width: 800,
          padding: EdgeInsets.all(16),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.start,
            children: [
              TextField(
                decoration: InputDecoration(
                  labelText: "Nama",
                  hintText: "Ketikkan Nama",
                  prefixIcon: Icon(Icons.text_fields),
                  fillColor: Colors.pink.shade200,
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(10),
                  ),
                ),
                controller: nama,
              ),
              SizedBox(height: 10),
              TextField(
                controller: email,
                decoration: InputDecoration(
                  labelText: "Email",
                  hintText: "Ketikkan Email",
                  prefixIcon: Icon(Icons.email_rounded),
                  fillColor: Colors.pink.shade200,
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(10),
                  ),
                ),
              ),
              SizedBox(height: 10),
              TextField(
                readOnly: true,
                decoration: InputDecoration(
                  labelText: "Tanggal Lahir",
                  hintText: "Pilih Tanggal Lahir",
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(10),
                  ),
                  fillColor: Colors.pink.shade200,
                  prefixIcon: Icon(Icons.calendar_today),
                ),
                onTap: () => _pilihTgl(context),
                controller: TextEditingController(
                  text: "${tglLahir.toLocal()}".split(" ")[0],
                ),
              ),
              SizedBox(height: 20),
              SizedBox(
                height: 50,
                width: 200,
                child: TextButton(
                  style: TextButton.styleFrom(
                    backgroundColor: Colors.pink.shade400,
                  ),
                  onPressed: () {
                    setState(() {
                      namaMhs = nama.text;
                      emailMhs = email.text;
                    });
                    insertMahasiswa();
                  },
                  child: Text(
                    "SIMPAN",
                    style: TextStyle(
                      color: Colors.white,
                      fontSize: 20,
                    ),
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
