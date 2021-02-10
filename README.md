<h1>このアプリについて</h1>
<p>これは刀の記録帳代わりのアプリです</p>
<h1>開発状況</h1>
<p>リスト表示まではできています</p>
<h1>課題</h1>u
<p>画像表示</p>
<p>保存する際の型についての検討</p>

@startuml
title 選択された画像を保存する流れ

start

:SAFを利用して画像を選択してもらう;

:選択された画像のURLを取得;
note left
    EditViewModelのurl（文字列型）に一時保存しておく
end note

:トリミングする;

:画像を反映;

if(保存するかどうか) then (yes)
    if(別の画像が保存されていた場合) then (yes)
        :その画像を消去する;
     else(no)
     endif

    :画像を外部ストレージに保存;
    :外部ストレージのURLを保存;
    note left
        EditViewModelを利用してUri形式で保存する
    end note
else (no)
    :選択された画像のURLを破棄;
endif
end
@enduml
