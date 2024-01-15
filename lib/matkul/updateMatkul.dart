// ignore_for_file: prefer_const_constructors, prefer_const_literals_to_create_immutables

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

// ignore: must_be_immutable
class UpdateMatkul extends StatefulWidget {
  int idup;
  String kodeup;
  String namaup;
  String sksup;
  UpdateMatkul(this.idup, this.kodeup, this.namaup, this.sksup);

  @override
  State<UpdateMatkul> createState() => _UpdateMatkulState();
}

class _UpdateMatkulState extends State<UpdateMatkul> {
  int id = 0;
  final kode = TextEditingController();
  final nama = TextEditingController();
  final sks = TextEditingController();

  @override
  void initState() {
    kode.text = widget.kodeup;
    nama.text = widget.namaup;
    sks.text = widget.sksup;
    id = widget.idup;

    super.initState();
  }

  bool isNumeric(String str) {
    // ignore: unnecessary_null_comparison
    if (str == null || str.isEmpty) {
      return false;
    }
    final format = RegExp(r'^[0-9]+$');
    return format.hasMatch(str);
  }

  Future<void> updateMatkul() async {
    if (isNumeric(sks.text)) {
      String urlUpdate =
          "http://192.168.56.1:9002/api/v1/matkul/${id}?kode=${kode.text}&&nama=${nama.text}&&sks=${sks.text}";
      try {
        var response = await http.put(Uri.parse(urlUpdate));

        if (response.statusCode == 200) {
          Navigator.pop(context);
        } else {
          print(response.statusCode);
        }
      } catch (e) {
        print(e);
      }
    } else {
      print("Bukan Angka");
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Update Data Matakuliah"),
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
                  labelText: "Kode",
                  hintText: "Ketikkan Kode Matakuliah",
                  prefixIcon: Icon(Icons.code),
                  fillColor: Colors.pink.shade200,
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(10),
                  ),
                ),
                controller: kode,
              ),
              SizedBox(height: 10),
              TextField(
                controller: nama,
                decoration: InputDecoration(
                  labelText: "Nama",
                  hintText: "Ketikkan Nama Matakuliah",
                  prefixIcon: Icon(Icons.book),
                  fillColor: Colors.pink.shade200,
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(10),
                  ),
                ),
              ),
              SizedBox(height: 10),
              TextField(
                controller: sks,
                decoration: InputDecoration(
                  labelText: "SKS",
                  hintText: "Ketikkan Jumlah SKS",
                  prefixIcon: Icon(Icons.numbers_rounded),
                  fillColor: Colors.pink.shade200,
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(10),
                  ),
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
                    updateMatkul();
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
