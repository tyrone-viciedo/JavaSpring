'use strict'

//画面ロード時の処理
jQuery(function($){

    //更新ボタン押下時の処理
    $('#btn-update').click(function(event){
        //ユーザ更新
        updateUser();
    });

    //削除ボタン押下時の処理
    $('#btn-delete').click(function(event){
        //ユーザ削除
        deleteUser();
    });
});

function updateUser(){

    //フォームの値を取得
    var formData = $('#user-detail-form').serializeArray();

    //ajax通信
    $.ajax({
        type:"PUT",
        cache:false,
        url:"/user/update",
        date:formData,
        dataType:"json",
    }).done(function(data){
        //ajax成功時の処理
        alert("ユーザを更新しました");
        //ユーザー一覧画面にリダイレクト
        window.location.href="/user/list";
    }).fail(function(jqXHR,textStatus,errorThrown){
        //ajax失敗時の処理
        alert("ユーザー更新に失敗しました")
    }).always(function(xhr){
        alert(xhr.status)
    })
}

//ユーザの削除処理
function deleteUser(){
    var formData = $("#user-detail-form").serializeArray();

    $.ajax({
        type:"DELETE",
        cache:false,
        url:"/user/delete",
        date:formData,
        dataType:"json",
    }).done(function(data){
        //ajax成功時の処理
        alert("ユーザを削除しました");
        //ユーザー一覧画面にリダイレクト
        window.location.href="/user/list";
    }).fail(function(jqXHR,textStatus,errorThrown){
        //ajax失敗時の処理
        alert("ユーザー削除に失敗しました")
    }).always(function(){

    })
}

