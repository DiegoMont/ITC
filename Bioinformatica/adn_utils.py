ADN_BASES = {
    'A': 'T',
    'T': 'A',
    'C': 'G',
    'G': 'C'
}

def transcribe(cadena_molde):
    cadena_result = ""
    for base in cadena_molde:
        cadena_result += ADN_BASES[base.upper()]
    return cadena_result

def print_adn_transcription(cadena_molde):
    cadena_complemento = transcribe(cadena_molde)
    print(cadena_molde)
    print(cadena_complemento)