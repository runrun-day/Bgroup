const preview = document.getElementById('preview');
const fileInput = document.getElementById('image');

fileInput.addEventListener('change', (e) => {
  const file = e.target.files[0];

  if (!file) {
    // ファイル未選択 → デフォルト画像に戻す
    preview.src = 'images/no_image.png';
    return;
  }

  if (!file.type.startsWith('image/')) {
    alert('画像ファイルを選択してください。');
    fileInput.value = ''; // 入力リセット
    preview.src = 'images/no_image.png';
    return;
  }

  const reader = new FileReader();
  reader.onload = () => {
    preview.src = reader.result;
  };
  reader.readAsDataURL(file);
});
